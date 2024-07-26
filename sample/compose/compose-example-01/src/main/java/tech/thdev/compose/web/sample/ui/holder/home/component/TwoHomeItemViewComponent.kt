package tech.thdev.compose.web.sample.ui.holder.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.thdev.compose.web.sample.R
import tech.thdev.compose.web.sample.ui.design.system.button.ExampleButton
import tech.thdev.compose.web.sample.ui.model.ListItem

@Composable
internal fun TwoHomeItemView(
    item: ListItem.Item,
    onRemove: () -> Unit,
    onEditMode: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray.copy(0.3f))
    ) {
        Row {
            Text(
                text = item.text,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )

            IconButton(
                onClick = {
                    onRemove()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "remove",
                )
            }
        }

        ExampleButton(
            text = "edit",
            onClick = onEditMode,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTwoHomeItemView() {
    var item by remember {
        mutableStateOf(ListItem.Item.NEW.copy(text = "message~!!!!\naaaa"))
    }

    TwoHomeItemView(
        item = item,
        onRemove = {
            // Do nothing
        },
        onEditMode = {
            item = item.copy(
                editMode = true,
            )
        }
    )
}
