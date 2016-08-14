package tech.thdev.webviewexample;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import tech.thdev.kotlin_example_01.base.view.BaseFragment;
import tech.thdev.webviewexample.webkit.CustomWebChromeClient;
import tech.thdev.webviewexample.webkit.CustomWebViewClient;
import tech.thdev.webviewjavascriptinterface.util.ActivityUtilKt;

/**
 * Created by tae-hwan on 8/14/16.
 */

public class MainFragment extends BaseFragment<MainContract.Presenter> implements MainContract.View, TextView.OnEditorActionListener, View.OnKeyListener {

    @BindView(R.id.web_view)
    WebView webView;

    private EditText etUrlView;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        etUrlView = (EditText) getActivity().findViewById(R.id.et_url_input);
        etUrlView.setOnEditorActionListener(this);

        webView.setOnKeyListener(this);
        webView.setWebChromeClient(new CustomWebChromeClient(getActivity()));
        webView.setWebViewClient(new CustomWebViewClient(getPresenter()));
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webView.loadUrl("about:blank");
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch (i) {
            case EditorInfo.IME_ACTION_GO:
                validationUrl();
                return true;

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Log.d("TAG", "keycode : " + i);
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            switch (i) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private void validationUrl() {
        if (getPresenter() != null) {
            getPresenter().validationUrl(etUrlView.getText().toString());
        }
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
        ActivityUtilKt.hideKeyboard(getContext(), etUrlView);
    }

    @Override
    public void setUrl(String url) {
        etUrlView.setText(url);
        etUrlView.selectAll();
    }
}
