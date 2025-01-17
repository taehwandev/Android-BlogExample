package tech.thdev.app.ui.first

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FirstViewModelTest {

    private val repository = mock<Repository>()

    private val viewModel = FirstViewModel(repository = repository)

    @Test
    fun `test initData`() {
        Assertions.assertEquals(UiState(data = ""), viewModel._state.value)
        Assertions.assertEquals(UiState(data = ""), viewModel.state.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test loadData`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        whenever(repository.getData()).thenReturn(10_000)

        viewModel.loadData()

        verify(repository).getData() // getData 잘 불러와졌는지

        Assertions.assertEquals("10100", viewModel.state.value.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test loadData - fail case`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        whenever(repository.getData()).thenAnswer {
            throw Exception("error!!!!!!")
        }

        viewModel.loadData()

        verify(repository).getData() // getData 잘 불러와졌는지

        Assertions.assertEquals("Error", viewModel.state.value.data)
    }
}
