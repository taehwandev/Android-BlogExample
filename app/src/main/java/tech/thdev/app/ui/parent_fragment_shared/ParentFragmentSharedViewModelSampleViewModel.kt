package tech.thdev.app.ui.parent_fragment_shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ParentFragmentSharedViewModelSampleViewModel : ViewModel() {

    private val _updateFragmentCount = MutableLiveData<Int>(0)

    private val _viewUpdate = MutableLiveData<String>()
    val viewUpdate: LiveData<String> get() = _viewUpdate

    private fun plusCount() {
        _updateFragmentCount.value = (_updateFragmentCount.value ?: 0) + 1
    }

    fun plus() {
        plusCount()
        _viewUpdate.value = "My ViewModel count ${_updateFragmentCount.value}"
    }

    fun childPlus(title: String) {
        plusCount()
        _viewUpdate.value = "$title ParentFragment Shared ViewModel count  ${_updateFragmentCount.value}"
    }
}