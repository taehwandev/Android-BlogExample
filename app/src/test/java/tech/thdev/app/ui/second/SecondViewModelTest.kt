package tech.thdev.app.ui.second

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tech.thdev.library.test.TestOnClickEvent
import kotlin.time.ExperimentalTime

internal class SecondViewModelTest {
    private val mockEvent = TestOnClickEvent()
    private val viewModel = SecondViewModel(mockEvent)

    // LiveData 이용을 위한 처리 - core-testing 함께 사용
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        // Dispatcher 상태를 Unconfined으로 변경
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalTime
    @Test
    fun testFlowButtonSecond() = runBlocking {
        viewModel.flowButtonSecond()
            .test {
                mockEvent.click()
                Assert.assertTrue(awaitItem())
                cancelAndConsumeRemainingEvents()
            }
    }

    @ExperimentalTime
    @Test
    fun testFlowButtonPlusCount() = runBlocking {
        viewModel.flowButtonPlusCount()
            .test {
                mockEvent.click()
                Assert.assertTrue(awaitItem())
                mockEvent.click()
                Assert.assertEquals(2, viewModel.updateCount.value)
                cancelAndConsumeRemainingEvents()
            }
    }
}