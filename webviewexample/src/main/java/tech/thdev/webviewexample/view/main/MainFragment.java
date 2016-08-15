package tech.thdev.webviewexample.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import tech.thdev.kotlin_example_01.base.view.BaseFragment;
import tech.thdev.webviewexample.R;
import tech.thdev.webviewexample.view.main.presenter.MainContract;
import tech.thdev.webviewexample.webkit.CustomWebChromeClient;
import tech.thdev.webviewexample.webkit.CustomWebView;
import tech.thdev.webviewexample.webkit.CustomWebViewClient;
import tech.thdev.webviewjavascriptinterface.util.ActivityUtilKt;

/**
 * Created by tae-hwan on 8/14/16.
 */

public class MainFragment extends BaseFragment<MainContract.Presenter> implements MainContract.View, TextView.OnEditorActionListener, View.OnKeyListener {

    @BindView(R.id.web_view)
    CustomWebView webView;

    private EditText etUrlView;
    private ContentLoadingProgressBar loadingProgressBar;

    private FloatingActionButton floatingActionButton;

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
        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });

        loadingProgressBar = (ContentLoadingProgressBar) getActivity().findViewById(R.id.web_view_load_progress_bar);

//        webView.setOnKeyListener(this);

        // Add WebView ScrollChangeListener M
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//
//                }
//            });
//        }

        webView.setOnWebViewListener(getPresenter());

        webView.setWebChromeClient(new CustomWebChromeClient(getActivity(), getPresenter()));
        webView.setWebViewClient(new CustomWebViewClient(getPresenter()));
        webView.defaultInit(WebSettings.LOAD_DEFAULT);
        if (getPresenter() != null) {
            getPresenter().defaultLoadUrl();
        }
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

    /**
     * WebView Can go back.
     */
    public boolean canGoBack() {
        return webView.canGoBack();
    }

    /**
     * WebView go back.
     */
    public void goBack() {
        webView.goBack();
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
    }

    @Override
    public void webViewScrollChanged(boolean isTop) {
        if (isTop) {
            floatingActionButton.show();
        } else {
            floatingActionButton.hide();
        }
    }

    @Override
    public void webViewProgressChanged(int newProgress) {
        loadingProgressBar.setProgress(newProgress);

        if (newProgress >= 100) {
            loadingProgressBar.setVisibility(View.GONE);
        } else if (loadingProgressBar.getVisibility() != View.VISIBLE) {
            loadingProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void urlShare() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, webView.getTitle());
        sendIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share link!"));
    }
}
