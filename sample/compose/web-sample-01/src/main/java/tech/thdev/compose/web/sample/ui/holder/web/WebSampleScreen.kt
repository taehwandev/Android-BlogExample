package tech.thdev.compose.web.sample.ui.holder.web

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun WebSampleScreen(
    chromeClient: WebChromeClient = remember { object : WebChromeClient() {} },
    client: WebViewClient = remember { object : WebViewClient() {} },
) {
    val context = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // WebView changes it's layout strategy based on
        // it's layoutParams. We convert from Compose Modifier to
        // layout params here.
        val width = if (constraints.hasFixedWidth) {
            ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            ViewGroup.LayoutParams.WRAP_CONTENT
        }
        val height = if (constraints.hasFixedHeight) {
            ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            ViewGroup.LayoutParams.WRAP_CONTENT
        }

        val layoutParams = FrameLayout.LayoutParams(
            width,
            height
        )

//        var webView by remember { mutableStateOf<WebView?>(null) }
        val webView = LocalWebOwner.current

        LaunchedEffect(key1 = Unit) {
            webView?.loadUrl("https://thdev.tech/")
        }

        AndroidView(
            factory = {
                Log.i("TEMP", "AndroidView factory - $webView")
                val parentLayout = FrameLayout(context).apply {
                    val web = webView ?: WebView(context).apply {
                        this.layoutParams = layoutParams
                        settings.run {
                            javaScriptEnabled = true
                            defaultTextEncodingName = "UTF-8"

                            cacheMode = WebSettings.LOAD_DEFAULT

                            setSupportMultipleWindows(true)
                        }

                        webChromeClient = chromeClient
                        webViewClient = client
                    }

//                    webView = web
                    addView(web)
                }
                parentLayout
            },
            update = {
                Log.i("TEMP", "AndroidView update - $webView")
            },
            onRelease = { parentFrame ->
                Log.i("TEMP", "AndroidView onRelease - $webView")
                (parentFrame.children.first() as? WebView)?.let {
                    parentFrame.removeView(it)
                }
            }
        )
    }
}
