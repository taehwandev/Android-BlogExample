package tech.thdev.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SampleAdapterSelectViewModel : ViewModel() {

    companion object {
        private const val SECTION_ONE = 1000
        private const val SECTION_TWO = 2000
    }

    private val _updateDiffDefault = MutableLiveData<List<SelectItem>>()
    val updateDiffDefault: LiveData<List<SelectItem>> get() = _updateDiffDefault

    private val _updateDiffId = MutableLiveData<List<SelectItem>>()
    val updateDiffId: LiveData<List<SelectItem>> get() = _updateDiffId

    private val _showSelect = MutableLiveData(false)
    val showSelect: LiveData<Boolean> get() = _showSelect

    fun loadItems() = viewModelScope.launch {
        _updateDiffDefault.value = createSampleOneDiffDefault()
        _updateDiffId.value = createSampleOneDiffId()
    }

    private fun createSampleOneDiffDefault(): List<SelectItem> {
        val list = mutableListOf<SelectItem>()
        for (index in 1..5) {
            if (index == 1) {
                list.add(SelectItem.Section(SECTION_ONE, "Section Diff equals only $index"))
            } else {
                list.add(SelectItem.Item(SECTION_ONE, "Diff equals only title $index", false))
            }
        }
        return list
    }

    private fun createSampleOneDiffId(): List<SelectItem> {
        val list = mutableListOf<SelectItem>()
        for (index in 1..5) {
            if (index == 1) {
                list.add(SelectItem.Section(SECTION_TWO, "Section Diff equals Id $index"))
            } else {
                list.add(SelectItem.Item(SECTION_TWO, "Diff equals id title $index", false))
            }
        }
        return list
    }

    fun onClickSelectVisibilityChange() {
        val change = (_showSelect.value ?: false).not()
        _updateDiffDefault.change(change)
        _updateDiffId.change(change)
        _showSelect.value = change
    }

    private fun MutableLiveData<List<SelectItem>>.change(change: Boolean) {
        this.value = this.value?.map {
            when (it) {
                is SelectItem.Section -> {
                    it.copy(showSelected = change)
                }
                is SelectItem.Item -> {
                    it.copy(showSelected = change)
                }
            }
        }
    }

    fun onClickSectionSelect(section: SelectItem.Section) {
        _updateDiffDefault.change(section)
        _updateDiffId.change(section)
    }

    private fun MutableLiveData<List<SelectItem>>.change(section: SelectItem.Section) {
        value = value?.map {
            when (it) {
                is SelectItem.Section -> {
                    if (it.sectionIndex == section.sectionIndex) {
                        it.copy(isSelect = it.isSelect.not())
                    } else {
                        it
                    }
                }
                is SelectItem.Item -> {
                    if (it.sectionIndex == section.sectionIndex) {
                        it.copy(isSelect = it.isSelect.not())
                    } else {
                        it
                    }
                }
            }
        }
    }

    sealed class SelectItem {

        data class Item(
            val sectionIndex: Int,
            val title: String,
            val isSelect: Boolean = false,
            val showSelected: Boolean = false
        ) : SelectItem()

        data class Section(
            val sectionIndex: Int,
            val title: String,
            val isSelect: Boolean = false,
            val showSelected: Boolean = false
        ) : SelectItem()
    }
}