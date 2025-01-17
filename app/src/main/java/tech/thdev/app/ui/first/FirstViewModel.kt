package tech.thdev.app.ui.first

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirstViewModel(
    private val repository: Repository,
) : ViewModel() {

    @VisibleForTesting
    val _state = MutableStateFlow(UiState(data = ""))
    val state = _state.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getData()
            }
                .onFailure {
                    _state.value = UiState(
                        data = "Error",
                    )
                }
                .onSuccess {
                    _state.value = UiState(
                        data = it.plus(100).toString(),
                    )
                }
        }
    }
}

data class UiState(
    val data: String,
)

interface Repository {

    suspend fun getData(): Int
}