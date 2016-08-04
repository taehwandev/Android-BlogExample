package tech.thdev.customwebview.webkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Tae-hwan on 8/4/16.
 */

public class CustomWebView extends WebView {

    public CustomWebView(Context context) {
        super(context);

        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {
        WebSettings settings = this.getSettings();
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
    }
}
