package tech.thdev.webviewexample.webkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import tech.thdev.webviewexample.webkit.listener.OnWebViewListener;

/**
 * Created by tae-hwan on 8/15/16.
 */

public class CustomWebView extends WebView {

    private OnWebViewListener webViewListener;

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnWebViewListener(OnWebViewListener listener) {
        this.webViewListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (webViewListener != null) {
            webViewListener.onScroll(l, t, oldl, oldt);
        }
    }

    public void defaultInit(int cacheMode) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDefaultTextEncodingName("UTF-8");

        // Setting Local Storage
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);

        // No Cache
        settings.setCacheMode(cacheMode);
    }
}
