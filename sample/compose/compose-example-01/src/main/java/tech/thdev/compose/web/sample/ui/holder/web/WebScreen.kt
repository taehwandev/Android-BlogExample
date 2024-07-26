package tech.thdev.compose.web.sample.ui.holder.web

import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children

@Composable
internal fun WebScreen() {
    val chromeClient = CustomWebChromeClient()
    val client = CustomWebViewClient()

    val webView = LocalWebOwner.current

    var url by remember { mutableStateOf("https://thdev.tech/") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            value = url,
            onValueChange = { new ->
                url = new
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    webView?.loadUrl(url)
                },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
        )

        BoxWithConstraints(
            modifier = Modifier
                .weight(1f)
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

            LaunchedEffect(key1 = Unit) {
                webView?.loadUrl(url)
            }

            BackHandler {
                if (webView?.canGoBack() == true) {
                    webView.goBack()
                }
            }

            val context = LocalContext.current

            AndroidView(
                factory = {
                    val parentLayout = FrameLayout(context).apply {
                        val web = webView ?: WebView(context)

                        web.apply {
                            this.layoutParams = layoutParams
                            settings.run {
                                javaScriptEnabled = true
                                defaultTextEncodingName = "UTF-8"
                                loadWithOverviewMode = true
                                useWideViewPort = true
                                setSupportZoom(true)

                                mixedContentMode =
                                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                                setNetworkAvailable(true)
                                cacheMode = WebSettings.LOAD_DEFAULT

                                setSupportMultipleWindows(true)
                            }

                            webChromeClient = chromeClient
                            webViewClient = client
                        }
                        addView(web)
                    }
                    parentLayout
                },
                onRelease = { parentFrame ->
                    (parentFrame.children.first() as? WebView)?.let { web ->
                        parentFrame.removeView(web)
                    }
                }
            )
        }
    }
}
