package tech.thdev.app.webkit

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by Tae-hwan on 8/4/16.
 */
class CustomWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleArr: Int = 0
) : WebView(context, attrs, defStyleArr) {

    fun init() {
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.displayZoomControls = false
        settings.setSupportZoom(false)
        settings.defaultTextEncodingName = "UTF-8"

        // Setting Local Storage
        settings.databaseEnabled = true
        settings.domStorageEnabled = true

        // No Cache
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
    }
}