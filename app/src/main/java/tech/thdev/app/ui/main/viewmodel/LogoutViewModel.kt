package tech.thdev.app.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.launch
import java.text.SimpleDateFormat
import java.util.*

class LogoutViewModel : ViewModel() {

    private val uiContext = UI
    private val bgContext = CommonPool

    lateinit var updateLoginTime: (time: String) -> Unit
    lateinit var logoutSuccess: () -> Unit

    private var prevLoginTime: Long = 0

    private val simpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("mm:ss.S", Locale.getDefault())
    }

    private lateinit var loginTimer: Job

    private fun updateTimeThread() = launch {
        while (isActive) {
            launch(uiContext) {
                if (::updateLoginTime.isInitialized) {
                    updateLoginTime(simpleDateFormat.format(System.currentTimeMillis() - prevLoginTime))
                }
            }
            Thread.sleep(10)
        }
    }

    fun startLoginTime() {
        prevLoginTime = System.currentTimeMillis()
        loginTimer = updateTimeThread()
    }

    fun stopLoginTime() {
        Log.e("TEMP", "stop")
        launch {
            loginTimer.cancelAndJoin()
            launch(uiContext) { logoutSuccess() }
        }
        Log.e("TEMP", "stop cancelAndJoin")
    }
}