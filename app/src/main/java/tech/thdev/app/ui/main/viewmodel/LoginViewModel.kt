package tech.thdev.app.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.selects.select
import tech.thdev.app.data.LoginItem
import tech.thdev.app.data.source.login.LoginDataSource
import tech.thdev.app.data.source.login.LoginRepository
import kotlin.coroutines.experimental.CoroutineContext

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    lateinit var statusChangeLoginButton: (isEnable: Boolean) -> Unit
    lateinit var loginSuccess: () -> Unit
    lateinit var loginFail: () -> Unit

    // dispatches execution onto the Android main UI thread
    private val uiContext: CoroutineContext = UI

    // represents a common pool of shared threads as the coroutine dispatcher
    private val bgContext: CoroutineContext = CommonPool

    private val checkUserChannel: Channel<LoginItem> by lazy {
        Channel<LoginItem>()
    }

    fun startTimerWatcher() {
        launch(bgContext) { checkUserInput(checkUserChannel) }
    }

    private suspend fun checkUserInput(receiveChannel: ReceiveChannel<LoginItem>) = produce<LoginItem> {
        // isActive only loop.
        while (isActive) {
            select<Unit> {
                receiveChannel.onReceive {
                    launch(uiContext) {
                        statusChangeLoginButton(it.userId.isNotEmpty() && it.password.isNotEmpty())
                    }
                }
            }
        }
    }

    fun updateInput(userId: String, userPassword: String) {
        launch(bgContext) { checkUserChannel.send(LoginItem(userId, userPassword)) }
    }

    fun startLogin(userId: String, userPassword: String) {
        launch {
            when (loginRepository.login(LoginItem(userId, userPassword))) {
                LoginDataSource.TYPE_SUCCESS -> launch(uiContext) { loginSuccess() }
                LoginDataSource.TYPE_DONT_MATCH -> launch(uiContext) { loginFail() }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        bgContext.cancelChildren()
        checkUserChannel.close()
    }
}
