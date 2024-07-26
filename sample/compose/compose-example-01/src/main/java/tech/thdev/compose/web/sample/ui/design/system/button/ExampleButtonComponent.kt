package tech.thdev.compose.web.sample.ui.design.system.button

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        modifier = modifier
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