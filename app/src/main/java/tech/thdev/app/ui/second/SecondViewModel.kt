package tech.thdev.app.ui.second

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.thdev.app.R
import tech.thdev.library.OnClickEventControl

class SecondViewModel : ViewModel() {

    private val _clickEvent = MutableLiveData<Boolean>()
    val clickEvent: LiveData<Boolean> get() = _clickEvent

    private val _updateCount = MutableLiveData<Int>(0)
    val updateCount: LiveData<Int> get() = _updateCount

    /**
     * 이렇게 동작할 수 있음을 보여주기 위한 코드입니다.
     * 참고만 하세요.
     */
    fun load(viewController: OnClickEventControl) {
        viewController.onClick(R.id.button_second)
            .onEach {
                Log.i(TAG, "Click next.")
                _clickEvent.value = true
            }
            .launchIn(viewModelScope)

        /**
         * Plus 버튼 이벤트 처리
         */
        viewController.onClick(R.id.btn_plus_count)
            .onEach {
                Log.i(TAG, "Click plus next.")
                _updateCount.value = (_updateCount.value ?: 0) + 1
            }
            .launchIn(viewModelScope)
    }

    /**
     * onResume/onRestart 처리를 위한 처리
     */
    fun eventEnd() {
        _clickEvent.value = false
    }

    companion object {
        const val TAG = "SecondViewModel"
    }
}