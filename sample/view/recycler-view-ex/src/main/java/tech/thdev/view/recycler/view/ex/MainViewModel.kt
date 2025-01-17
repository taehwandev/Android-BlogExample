package tech.thdev.view.recycler.view.ex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.thdev.view.recycler.view.ex.model.SampleItem

class MainViewModel : ViewModel() {

    private val _listOne = MutableStateFlow(emptyList<SampleItem>())
    val listOne get() = _listOne.asStateFlow()

    private val _trashList = MutableStateFlow(emptyList<SampleItem>())
    val trashList get() = _trashList.asStateFlow()

    fun loadData() {
        _listOne.value = (1..10).map {
            SampleItem(
                id = it,
                message = "Index $it",
                icon = R.drawable.baseline_perm_media_24,
            )
        }
    }

    fun actionClick(position: Int) = viewModelScope.launch {
        if (position in listOne.value.indices) {
            val item = listOne.value[position]
            _trashList.update { list ->
                list.toMutableList().also { newList ->
                    newList.add(item.copy(trash = true, time = 5))
                }
            }

            _listOne.update { list ->
                list.toMutableList().also { newList ->
                    newList.remove(item)
                }
            }

            runTimer(item) // 타이머 시작
        }
    }

    fun actionClickTwo(position: Int) = viewModelScope.launch {
        if (position in trashList.value.indices) {
            val item = trashList.value[position]
            updateTrash(item)
        }
    }

    private fun updateTrash(item: SampleItem) {
        item.job?.cancel()
        item.job = null

        _trashList.update { list ->
            list.toMutableList().also { newList ->
                newList.remove(item)
            }
        }

        _listOne.update { list ->
            list.toMutableList().also { newList ->
                newList.add(item.copy(trash = false, time = 5)) // 복구
            }
        }
    }

    private fun runTimer(oldItem: SampleItem) {
        if (oldItem.time == 0) {
            updateTrash(oldItem)
            return
        }

        oldItem.job?.cancel()
        oldItem.job = null

        oldItem.job = viewModelScope.launch {
            delay(1_000) // 1초 후 실행

            var copyItem: SampleItem? = null
            _trashList.update {
                it.map { item ->
                    if (oldItem.id == item.id) {
                        item.copy(time = oldItem.time - 1).also { copy ->
                            copyItem = copy
                        }
                    } else {
                        item
                    }
                }
            }

            copyItem?.let {
                runTimer(it)
            } ?: run {
                oldItem.job = null
            }
        }
    }
}
