package tech.thdev.compose.web.sample.ui.holder.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import tech.thdev.compose.web.sample.ui.model.ListItem

/**
 * 1 - HomeScreen 분리
 * 2 - Preview 작성
 * 3 - Preview 확인
 * 4. Stateful versus stateless 설명
 * 5. stateful 코드 추가
 * 6. stateless 코드 정의 및 프리뷰 정의
 */
@Composable
internal fun HomeScreenOne() {
    var listItem by remember { mutableStateOf(ListItem(emptyList())) }

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
                                        listItem = listItem.copy(
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
                                        listItem = listItem.copy(
                                            items = listItem.items.toMutableList().also { newList ->
                                                newList.remove(item)
                                            },
                                        )
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
                    } else {
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
                                        listItem = listItem.copy(
                                            items = listItem.items.toMutableList().also { newList ->
                                                newList.remove(item)
                                            },
                                        )
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_close_24),
                                        contentDescription = "remove",
                                    )
                                }
                            }

                            Button(
                                onClick = {
                                    listItem = listItem.copy(
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
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 10.dp, bottom = 16.dp)
                            ) {
                                Text(
                                    text = "edit",
                                )
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                listItem = listItem.copy(
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

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreenOne() {
    HomeScreenOne()
}
