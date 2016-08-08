package tech.thdev.webviewjavascriptinterface.view.main;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.kotlin_example_01.base.view.BaseFragment;
import tech.thdev.webviewjavascriptinterface.R;
import tech.thdev.webviewjavascriptinterface.view.main.presenter.MainContract;
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebChromeClient;
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebView;
import tech.thdev.webviewjavascriptinterface.webkit.CustomWebViewClient;

/**
 * Created by Tae-hwan on 8/8/16.
 */

public class MainFragment extends BaseFragment<MainContract.Presenter> implements MainContract.View, TextView.OnEditorActionListener {

    private static final String DEFAULT_URL = "http://thdev.tech/sample/webview_sample.html";

    @BindView(R.id.web_view)
    CustomWebView webView;

    @BindView(R.id.et_keyword)
    EditText etKeyword;

    private EditText etUrl;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(@org.jetbrains.annotations.Nullable View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        initView();
    }

    private void initView() {
        etUrl = (EditText) getActivity().findViewById(R.id.et_url);
        etUrl.setOnEditorActionListener(this);
        etUrl.setText(DEFAULT_URL);

        etKeyword.setOnEditorActionListener(this);

        webView.setWebViewClient(new CustomWebViewClient());
        webView.setWebChromeClient(new CustomWebChromeClient(getActivity()));

        /*
         * This method can be used to allow JavaScript to control the host application.
         * This is a powerful feature, but also presents a security risk for apps targeting JELLY_BEAN or earlier.
         * Apps that target a version later than JELLY_BEAN are still vulnerable if the app runs on a device running
         * Android earlier than 4.2. The most secure way to use this method is to target JELLY_BEAN_MR1 and to ensure
         * the method is called only when running on Android 4.2 or later.
         * With these older versions, JavaScript could use reflection to access an injected object's public fields.
         * Use of this method in a WebView containing untrusted content could allow an attacker to manipulate
         * the host application in unintended ways, executing Java code with the permissions of the host application.
         * Use extreme care when using this method in a WebView which could contain untrusted content.
         */
        if (getPresenter() != null) {
            webView.addJavascriptInterface(getPresenter().getCustomJavaScript(), "WebViewTest");
        }
        webView.init();

        loadUrl(DEFAULT_URL);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch (i) {
            case EditorInfo.IME_ACTION_SEND:
                updateKeyword();
                return true;

            case EditorInfo.IME_ACTION_GO:
                String url = etUrl.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    url = DEFAULT_URL;
                }
                loadUrl(url);
                hideKeyboard(etUrl);
                return true;

            default:
                break;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_reload, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reload:
                webView.reload();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadUrl(String url) {
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void updateKeyword(final String keyword) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etKeyword.setText(keyword);
            }
        });
    }

    @OnClick(R.id.btn_search)
    public void onBtnSearch(View view) {
        updateKeyword();
        hideKeyboard(view);
    }

    private void updateKeyword() {
        loadUrl("javascript:updateKeyword('" + etKeyword.getText().toString() + "')");
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
