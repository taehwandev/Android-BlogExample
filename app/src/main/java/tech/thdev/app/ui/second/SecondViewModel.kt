package tech.thdev.app.ui.second

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.thdev.app.R
import tech.thdev.library.OnClickEventControl
import kotlin.coroutines.CoroutineContext

class SecondViewModel(
    private val viewController: OnClickEventControl
) : DefaultLifecycleObserver {

    private val coroutineScope = object : CoroutineScope {

        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + SupervisorJob()
    }

    private val _clickEvent = MutableLiveData<Boolean>()
    val clickEvent: LiveData<Boolean> get() = _clickEvent

    private val _updateCount = MutableLiveData(0)
    val updateCount: LiveData<Int> get() = _updateCount

    override fun onCreate(owner: LifecycleOwner) {
        flowButtonSecond()
            .launchIn(coroutineScope)


        flowButtonPlusCount()
            .launchIn(coroutineScope)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        coroutineScope.coroutineContext.cancel()
    }

    /**
     * Second 버튼 이벤트 처리
     */
    fun flowButtonSecond(): Flow<Boolean> =
        viewController.onClick(R.id.button_second)
            .onEach {
                Log.i(TAG, "Click next.")
                _clickEvent.value = true
            }

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