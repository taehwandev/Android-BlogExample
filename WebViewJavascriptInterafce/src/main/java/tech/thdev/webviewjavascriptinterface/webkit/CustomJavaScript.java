package tech.thdev.webviewjavascriptinterface.webkit;

import android.webkit.JavascriptInterface;

import tech.thdev.webviewjavascriptinterface.webkit.listener.OnCustomJavaScriptListener;

/**
 * Created by Tae-hwan on 8/8/16.
 */

public class CustomJavaScript {

    private OnCustomJavaScriptListener listener;

    public CustomJavaScript() {

    }

    public void setOnCustomJavascriptListener(OnCustomJavaScriptListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void showSearch(String keyword) {
        if (listener != null) {
            listener.onShowSearch(keyword);
        }
    }
}
