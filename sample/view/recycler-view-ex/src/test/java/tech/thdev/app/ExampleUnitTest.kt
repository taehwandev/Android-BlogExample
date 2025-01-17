package tech.thdev.app

import android.view.View
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import tech.thdev.app.ui.JavaJava
import tech.thdev.app.ui.SamplA

/**
 * Example junit5 unit test, which will execute on the development machine (host).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        Assertions.assertEquals(4, 2 + 2)
//        println("java ${JavaJava.getValue()}")
        println("java ${JavaJava.value}")
        println("new")
        val java = JavaJava.getInstance()
        println("java $java")
        println("SampleA A ${SamplA.A}")
    }

    @Test
    fun test() = runTest {
        val a = MutableStateFlow(0)
        launch {
            (0..9).forEach {
                a.value = it
                kotlinx.coroutines.delay(1)
            }
        }

            launch {
                a.collect {
                    println("a -- $it")
                }
            }

        delay(1)
        launch {
            a.collect {
                println("b -- $it")
            }
        }
        delay(1)
        launch {
            a.collect {
                println("c -- $it")
            }
        }
    }
}

