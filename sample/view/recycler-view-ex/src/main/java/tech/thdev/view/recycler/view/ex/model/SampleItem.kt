package tech.thdev.view.recycler.view.ex.model

import androidx.annotation.DrawableRes
import kotlinx.coroutines.Job

data class SampleItem(
    val id: Int,
    val message: String,
    @DrawableRes val icon: Int,
    val time: Int = 5,
    val trash: Boolean = false,
) {

    var job: Job? = null
}
