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
    public void updateKeyword(String keyword) {
        if (listener != null) {
            listener.onUpdateKeyword(keyword);
        }
    }

    @JavascriptInterface
    public void changeWebView(String url) {
        if (listener != null) {
            listener.onChangeWebView(url);
        }
    }

    @JavascriptInterface
    public void hideKeyboard() {
        if (listener != null) {
            listener.hideKeyboard();
        }
    }
}
