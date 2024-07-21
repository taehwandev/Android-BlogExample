package tech.thdev.app.ui.holder.other

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun OtherSampleScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "other screen",
        )
    }
}

@Preview(
    backgroundColor = 0xFFFFFF,
)
@Composable
private fun PreviewOtherSampleScreen() {
    OtherSampleScreen()
}