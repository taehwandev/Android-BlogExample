package tech.thdev.compose.web.sample.ui.design.system.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

object ExampleButtonDefaults {

    val MinHeight = 50.dp

    val ContentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)

    val defaultTextStyle: TextStyle @Composable get() = MaterialTheme.typography.bodyMedium

    @Composable
    fun filledButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.secondary,
        contentColor: Color = MaterialTheme.colorScheme.onSecondary,
    ): ExampleButtonColors = ExampleButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )
}

@Immutable
data class ExampleButtonColors internal constructor(
    private val containerColor: Color,
    private val contentColor: Color,
) {

    @Composable
    internal fun containerColor(): State<Color> {
        return rememberUpdatedState(containerColor)
    }

    @Composable
    internal fun contentColor(): State<Color> {
        return rememberUpdatedState(contentColor)
    }
}
