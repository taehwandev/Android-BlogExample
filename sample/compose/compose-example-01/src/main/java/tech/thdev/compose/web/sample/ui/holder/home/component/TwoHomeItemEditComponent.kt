package tech.thdev.compose.web.sample.ui.holder.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.thdev.compose.web.sample.ui.design.system.button.ExampleButton
import tech.thdev.compose.web.sample.ui.model.ListItem

@Composable
internal fun TwoHomeItemEdit(
    item: ListItem.Item,
    onEditModeOff: (changeItem: ListItem.Item) -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var changeItem by remember(item) { mutableStateOf(item) }
        TextField(
            value = changeItem.text,
            onValueChange = { new ->
                changeItem = changeItem.copy(
                    text = new,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Row {
            ExampleButton(
                text = "Save",
                onClick = { onEditModeOff(changeItem) },
                modifier = Modifier
                    .weight(1f)
            )

            ExampleButton(
                text = "X",
                onClick = onCancel,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTwoHomeItemEdit() {
    var item by remember {
        mutableStateOf(ListItem.Item.NEW)
    }
    TwoHomeItemEdit(
        item = item,
        onEditModeOff = { changeItem ->
            item = changeItem.copy(
                editMode = false,
            )
        },
        onCancel = {
            item = ListItem.Item.NEW
        },
    )
}
