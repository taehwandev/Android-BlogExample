package tech.thdev.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _updateActivityCount = MutableLiveData<Int>(0)
    val updateActivityCount: LiveData<Int> get() = _updateActivityCount

    fun insertCountTwo() {
        _updateActivityCount.value = (updateActivityCount.value ?: 0) + 2
    }
}