package tech.thdev.architecture.app.common.snackbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import tech.thdev.architecture.app.common.snackbar.model.SnackbarUiState
import tech.thdev.architecture.app.snackbar.repository.api.InternalSnackbarRepository
import tech.thdev.architecture.app.snackbar.repository.api.model.SnackbarEndEvent
import javax.inject.Inject

@HiltViewModel
class SnackbarViewModel @Inject constructor(
    private val internalSnackbarRepository: InternalSnackbarRepository,
) : ViewModel() {

    private val _snackbarUiState = MutableStateFlow<SnackbarUiState?>(null)
    val snackbarUiState = _snackbarUiState.asStateFlow()

    private val flowShow = internalSnackbarRepository.show()
        .map {
            SnackbarUiState(
                message = it.message,
                actionLabel = it.actionLabel,
            )
        }
        .onEach {
            _snackbarUiState.value = it
        }
        .flowOn(Dispatchers.Default)

    fun load() {
        flowShow
            .launchIn(viewModelScope)
    }

    fun actionDismiss() {
        _snackbarUiState.value = null
        internalSnackbarRepository.endEvent(SnackbarEndEvent.Dismiss)
    }

    fun actionPerformed() {
        _snackbarUiState.value = null
        internalSnackbarRepository.endEvent(SnackbarEndEvent.Performed)
    }
}
