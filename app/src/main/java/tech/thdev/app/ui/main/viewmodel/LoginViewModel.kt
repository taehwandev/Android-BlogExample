package tech.thdev.app.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.CoroutineContext

class LoginViewModel : ViewModel() {

    private fun out(message: String) {

        (0..5).forEach {
            Thread.sleep(100)
            println(message)
        }
    }

    fun coroutinesExample() {
        out("hi!!!!!!!!!!")
    }
}
