package tech.thdev.compose.web.sample.ui.holder.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.thdev.compose.web.sample.ui.model.ListItem

@Composable
internal fun HomeItemEdit(
    item: ListItem.Item,
    onEditModeOff: (changeItem: ListItem.Item) -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var changeItem by remember { mutableStateOf(item) }
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
            Button(
                onClick = {
                    onEditModeOff(changeItem)
//                    onEvent(
//                        listItem.copy(
//                            items = listItem.items.map { listItem ->
//                                if (listItem.index == item.index) {
//                                    changeItem.copy(
//                                        editMode = false,
//                                    )
//                                } else {
//                                    listItem
//                                }
//                            },
//                        )
//                    )
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Save",
                )
            }

            Button(
                onClick = {
                    onCancel()
//                    onEvent(
//                        listItem.copy(
//                            items = listItem.items.toMutableList().also { newList ->
//                                newList.remove(item)
//                            },
//                        )
//                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "X",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeItemEdit() {
    var item by remember {
        mutableStateOf(ListItem.Item.NEW)
    }
    HomeItemEdit(
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
