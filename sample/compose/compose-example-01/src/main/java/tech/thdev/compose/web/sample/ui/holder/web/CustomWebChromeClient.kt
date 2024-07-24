package tech.thdev.compose.web.sample.ui.holder.web

import android.os.Message
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebView.WebViewTransport
import android.webkit.WebViewClient

class CustomWebChromeClient : WebChromeClient() {

    override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean =
        if (view != null && resultMsg != null) {
            val newWebView = WebView(view.context).apply {
                webViewClient = object : WebViewClient() {

                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean =
                        request?.url?.let {
                            if (request.isRedirect) {
                                view?.loadUrl(it.toString())
                                true
                            } else {
                                view?.loadUrl(it.toString())
                                true
                            }
                        } ?: false
                }
            }
            (resultMsg.obj as WebViewTransport).apply {
                webView = newWebView
            }
            resultMsg.sendToTarget()
            true
        } else {
            super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }
}
