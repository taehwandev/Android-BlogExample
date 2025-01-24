package tech.thdev.compose.web.sample.ui.design.system.button

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Run() {
    Splash(
        run = {
            // ...
        }
    )
}

@Composable
fun Splash(
    run: () -> Unit,
) {
    val innerRun = remember { mutableStateOf(run) }


    SideEffect {
        Log.i("TEMP", "...")

    }

    DisposableEffect(innerRun) {
        onDispose {  }
    }

    LaunchedEffect(innerRun) {
        delay(3_000)
        run()
    }

    /// ...
}

@Composable
fun ExampleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ExampleButtonDefaults.ContentPadding,
    textStyle: TextStyle = ExampleButtonDefaults.defaultTextStyle,
    colors: ExampleButtonColors = ExampleButtonDefaults.filledButtonColors(),
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.containerColor().value,
            contentColor = colors.contentColor().value,
        ),
        enabled = enabled,
        modifier = Modifier
            .then(modifier)
            .defaultMinSize(minHeight = ExampleButtonDefaults.MinHeight)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = colors.contentColor().value,
            modifier = Modifier
                .padding(contentPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewExampleButton() {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        ExampleButton(
            onClick = {},
            text = "Button",
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            ExampleButton(
                onClick = {},
                text = "Button",
                modifier = Modifier
                    .weight(1f),
            )

            ExampleButton(
                onClick = {},
                text = "Button two",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
            )
        }
    }
}