package tech.thdev.app.ui.main.viewmodel

import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.selects.select
import org.junit.Test
import tech.thdev.app.data.LoginItem

class LoginViewModelTest {

    private suspend fun inputCheck(input: ReceiveChannel<LoginItem>) = produce<LoginItem> {
        while (isActive) {
            select<Unit> {
                input.onReceive {
                    println("item $it")
                }
            }
        }
    }

    @Test
    fun test() = runBlocking<Unit> {
        val chan = Channel<LoginItem>()
        launch(coroutineContext) {
            for (s in inputCheck(chan)) {

            }
        }
        chan.send(LoginItem("a", "b)"))
        chan.send(LoginItem("ab", "b)"))
        chan.send(LoginItem("abc", "b)"))
        chan.send(LoginItem("abcd", "b)"))
        chan.close()
    }
}