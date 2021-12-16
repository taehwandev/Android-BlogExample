package tech.thdev.second.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.thdev.base.BaseViewModel
import tech.thdev.library.OnClickEventControl

class SecondViewModel(
    viewController: OnClickEventControl
) : BaseViewModel(viewController) {

    private val _clickEvent = MutableLiveData<Boolean>()
    val clickEvent: LiveData<Boolean> get() = _clickEvent

    private val _updateCount = MutableLiveData(0)
    val updateCount: LiveData<Int> get() = _updateCount

    override fun onCreate() {
        // todo
//        flowButtonSecond()
//            .launchIn(coroutineScope)


        flowButtonPlusCount()
            .launchIn(coroutineScope)
    }

    /**
     * Second 버튼 이벤트 처리
     */
    // todo
//    fun flowButtonSecond(): Flow<Boolean> =
//        viewController.onClick(R.id.button_second)
//            .onEach {
//                Log.i(TAG, "Click next.")
//                _clickEvent.value = true
//            }

    /**
     * Plus 버튼 이벤트 처리
     */
    fun flowButtonPlusCount(): Flow<Boolean> =
        viewController.onClick(R.id.btn_plus_count)
            .onEach {
                Log.i(TAG, "Click plus next.")
                _updateCount.value = (_updateCount.value ?: 0) + 1
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