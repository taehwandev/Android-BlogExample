package tech.thdev.webviewjavascriptinterface.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
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

public class MainFragment extends BaseFragment<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.web_view)
    CustomWebView webView;

//    @BindView(R.id.et_keyword)
//    EditText etKeyword;

    private MainContract.Presenter presenter;

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

        initView();
    }

    private void initView() {
//        etKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (keyEvent.getAction() == KeyEvent.KEYCODE_SEARCH) {
//                    updateKeyword();
//                }
//                return false;
//            }
//        });

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
        webView.addJavascriptInterface(presenter.getCustomJavaScript(), "WebViewTest");
        webView.init();

        loadUrl("http://thdev.tech/sample/webview_sample.html");
    }

    @Override
    public void loadUrl(String url) {
        if (webView != null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void updateKeyword(String keyword) {
//        etKeyword.setText(keyword);
    }

//    @OnClick(R.id.btn_search)
//    public void onBtnSearch(View view) {
//        updateKeyword();
//    }

    void updateKeyword() {
//        loadUrl("javascript:updateKeyword('" + etKeyword.getText().toString() + "')");
    }

    @Override
    public void onPresenter(@Nullable MainContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
