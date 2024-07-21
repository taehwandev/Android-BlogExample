package tech.thdev.compose.web.sample.ui.holder.web

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf

internal object LocalWebOwner {

    private val LocalComposition = staticCompositionLocalOf<WebView?> { null }

    val current: WebView?
        @Composable
        get() = LocalComposition.current

    infix fun provides(registerOwner: WebView?): ProvidedValue<WebView?> =
        LocalComposition provides registerOwner
}
