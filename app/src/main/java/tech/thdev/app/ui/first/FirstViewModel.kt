package tech.thdev.app.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel : ViewModel() {

    private val _updateViewItem = MutableLiveData<String>()
    val updateViewItem: LiveData<String> get() = _updateViewItem

    private var count: Int = 0

    fun load() {
        _updateViewItem.value = "Now count ${count++}"
    }
}