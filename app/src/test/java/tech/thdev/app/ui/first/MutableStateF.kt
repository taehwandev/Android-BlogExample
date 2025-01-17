package tech.thdev.app.ui.first

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MutableState : State {

    override var value: String = ""
}

interface State {

    val value: String
}

class ReadOnlyState(state: State) : State by state

fun MutableState.asStateFlow(): State =
    ReadOnlyState(this)

class MutableStateF {

    @Test
    fun test() = runTest {
        val state = MutableState().asStateFlow()
//        Assertions.assertTrue(state is MutableState)

        val runFlow = flow {
            emit("a")
            emit("b")
            emit("c")
        }
        val stateFlow = runFlow.stateIn(
            scope = this,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(),
            initialValue = ""
        )
            .collect {
                println(it)
            }
//        val stateFlow = MutableStateFlow("")
//        val newStateFlow = stateFlow.asStateFlow()
//        val new = stateFlow
//            .map {
//                "$it + "
//            }
//            .onEach {
//                println(it)
//            }
//
//        Assertions.assertTrue(new is StateFlow<String>)
//        val a = newStateFlow as StateFlow<String>

        println(state.value)
    }
}