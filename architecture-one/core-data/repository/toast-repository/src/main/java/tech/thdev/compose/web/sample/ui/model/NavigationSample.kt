package tech.thdev.compose.web.sample.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class NavigationSample(
    val title: String,
    val icon: Int,
    val trigger: Trigger
) {

    enum class Trigger {
        HOME,
        WEB,
    }
}
