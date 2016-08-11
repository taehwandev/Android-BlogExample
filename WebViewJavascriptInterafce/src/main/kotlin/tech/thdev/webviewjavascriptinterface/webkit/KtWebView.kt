package tech.thdev.webviewjavascriptinterface.webkit

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by Tae-hwan on 8/11/16.
 */
class KtWebView: WebView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleArr: Int) : super(context, attrs, defStyleArr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleArr: Int, defStyleRes: Int) : super(context, attrs, defStyleArr, defStyleRes)

    fun init() {
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.displayZoomControls = false
        settings.setSupportZoom(true)
        settings.defaultTextEncodingName = "UTF-8"

        // Setting Local Storage
        settings.databaseEnabled = true
        settings.domStorageEnabled = true

        // No Cache
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
    }
}