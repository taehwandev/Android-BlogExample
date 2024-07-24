package tech.thdev.compose.web.sample.ui.holder.web

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient : WebViewClient() {

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
