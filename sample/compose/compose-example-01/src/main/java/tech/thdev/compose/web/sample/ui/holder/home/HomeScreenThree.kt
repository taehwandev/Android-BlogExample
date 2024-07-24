package tech.thdev.compose.web.sample.ui.holder.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.thdev.compose.web.sample.ui.holder.home.component.HomeItemEdit
import tech.thdev.compose.web.sample.ui.holder.home.component.HomeItemView
import tech.thdev.compose.web.sample.ui.model.ListItem

/**
 * 1 - HomeScreen 분리
 * 2 - Preview 작성
 * 3 - Preview 확인
 * 4. Stateful versus stateless 설명
 * 5. stateful 코드 추가
 * 6. stateless 코드 정의 및 프리뷰 정의
 * 7. 컴포넌트 분리
 */
@Composable
internal fun HomeScreenThree() {
    var listItem by remember { mutableStateOf(ListItem(emptyList())) }

    HomeScreenThree(
        listItem = listItem,
        onEvent = {
            listItem = it
        }
    )
}

/**
 * stateless
 */
@Composable
private fun HomeScreenThree(
    listItem: ListItem,
    onEvent: (listItem: ListItem) -> Unit,
) {
    Column {
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(listItem.items) { item ->
                Surface(
                    shape = MaterialTheme.shapes.small,
                ) {
                    if (item.editMode) {
                        HomeItemEdit(
                            item = item,
                            onEditModeOff = { changeItem ->
                                onEvent(
                                    listItem.copy(
                                        items = listItem.items.map { listItem ->
                                            if (listItem.index == item.index) {
                                                changeItem.copy(
                                                    editMode = false,
                                                )
                                            } else {
                                                listItem
                                            }
                                        },
                                    )
                                )
                            },
                            onCancel = {
                                onEvent(
                                    listItem.copy(
                                        items = listItem.items.toMutableList().also { newList ->
                                            newList.remove(item)
                                        },
                                    )
                                )
                            }
                        )
                    } else {
                        HomeItemView(
                            item = item,
                            onRemove = {
                                onEvent(
                                    listItem.copy(
                                        items = listItem.items.toMutableList().also { newList ->
                                            newList.remove(item)
                                        },
                                    )
                                )
                            },
                            onEditMode = {
                                onEvent(
                                    listItem.copy(
                                        items = listItem.items.map { listItem ->
                                            if (listItem == item) {
                                                listItem.copy(
                                                    editMode = true,
                                                )
                                            } else {
                                                listItem
                                            }
                                        },
                                    )
                                )
                            },
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                onEvent(
                    listItem.copy(
                        items = listItem.items.toMutableList().also { list ->
                            list.add(
                                ListItem.Item(
                                    index = list.size,
                                    text = "",
                                    editMode = true,
                                )
                            )
                        },
                    )
                )
            },
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "New",
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewHomeScreenThree() {
    var listItem by remember {
        mutableStateOf(
            ListItem(
                listOf(
                    ListItem.Item(
                        index = 0,
                        text = "message",
                        editMode = false,
                    ),
                    ListItem.Item(
                        index = 1,
                        text = "",
                        editMode = true,
                    ),
                )
            )
        )
    }
    HomeScreenThree(
        listItem = listItem,
        onEvent = {
            listItem = it
        }
    )
}
