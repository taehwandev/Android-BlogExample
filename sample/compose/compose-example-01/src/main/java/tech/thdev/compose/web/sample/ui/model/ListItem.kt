package tech.thdev.compose.web.sample.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class ListItem(
    val items: List<Item>,
) {

    @Immutable
    data class Item(
        val index: Int,
        val text: String,
        val editMode: Boolean,
    ) {

        companion object {

            val NEW = Item(
                index = 0,
                text = "",
                editMode = true,
            )
        }
    }
}
