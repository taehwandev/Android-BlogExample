package tech.thdev.app.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.suspendAtomicCancellableCoroutine
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.suspendCoroutine

class LoginViewModel : ViewModel() {

    lateinit var updateTime: (time: String) -> Unit

    // dispatches execution onto the Android main UI thread
    private val uiContext: CoroutineContext = UI

    // represents a common pool of shared threads as the coroutine dispatcher
    private val bgContext: CoroutineContext = CommonPool

    suspend fun loginUser() {
    }

    fun loadTime() = launch(uiContext) {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        (0..1000).forEach {
            println("start9")
            updateTime(simpleDateFormat.format(System.currentTimeMillis()))
            async(bgContext) { Thread.sleep(100)}.run { await() }

            println("end")
        }
    }

    fun updateInput(input: String) {

    }
}
