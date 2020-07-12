package tech.thdev.app.ui.activity_shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivitySharedViewModelSampleViewModel : ViewModel() {

    private val _updateFragmentCount = MutableLiveData<Int>(0)
    val updateFragmentCount: LiveData<Int> get() = _updateFragmentCount

    fun plus() {
        _updateFragmentCount.value = (updateFragmentCount.value ?: 0) + 1
    }
}