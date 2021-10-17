package tech.thdev.app.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel : ViewModel() {

    private val _clickEvent = MutableLiveData<Boolean>()
    val clickEvent: LiveData<Boolean> get() = _clickEvent

    /**
     * DataBinding을 이용해 호출할 함수
     */
    fun nextEvent() {
        _clickEvent.value = true
    }

    /**
     * onResume/onRestart 처리를 위한 처리
     */
    fun eventEnd() {
        _clickEvent.value = false
    }
}