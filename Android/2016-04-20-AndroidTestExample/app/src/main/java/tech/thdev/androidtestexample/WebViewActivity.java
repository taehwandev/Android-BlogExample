package tech.thdev.androidtestexample;

import android.webkit.WebView;

import butterknife.Bind;
import tech.thdev.androidtestexample.base.BaseActivity;
import tech.thdev.androidtestexample.constant.Constant;

/**
 * Created by Tae-hwan on 4/20/16.
 */
public class WebViewActivity extends BaseActivity {

    @Bind(R.id.web_view)
    WebView webView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate() {
        String url = getIntent().getStringExtra(Constant.INTENT_URL);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
