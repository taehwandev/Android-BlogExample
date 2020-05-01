package tech.thdev.app.webkit

import android.webkit.JavascriptInterface
import tech.thdev.app.webkit.listener.OnCustomJavaScriptListener

/**
 * Created by Tae-hwan on 8/8/16.
 */
class CustomJavaScript {

    private var listener: OnCustomJavaScriptListener? = null

    fun setOnCustomJavascriptListener(listener: OnCustomJavaScriptListener) {
        this.listener = listener
    }

    @JavascriptInterface
    fun updateKeyword(keyword: String) {
        listener?.onUpdateKeyword(keyword)
    }

    @JavascriptInterface
    fun changeWebView(url: String) {
        listener?.onChangeWebView(url)
    }

    @JavascriptInterface
    fun hideKeyboard() {
        listener?.hideKeyboard()
    }
}