package tech.thdev.architecture.app.common.alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import tech.thdev.architecture.app.alert.repository.api.InternalAlertRepository
import tech.thdev.architecture.app.alert.repository.api.model.AlertEndEvent
import tech.thdev.architecture.app.common.alert.model.AlertDialogUiState
import javax.inject.Inject

@HiltViewModel
class AlertDialogViewModel @Inject constructor(
    private val internalAlertRepository: InternalAlertRepository,
) : ViewModel() {

    private val _alertDialogUiState = MutableStateFlow(AlertDialogUiState.Default)
    val alertDialogUiState = _alertDialogUiState.asStateFlow()

    private val flowShowAlert = internalAlertRepository.showAlert()
        .map {
            AlertDialogUiState(
                title = it.title,
                message = it.message,
                confirmButtonText = it.confirmButtonText,
                dismissButtonText = it.dismissButtonText,
                show = true,
            )
        }
        .onEach {
            _alertDialogUiState.value = it
        }

    fun load() {
        flowShowAlert
            .launchIn(viewModelScope)
    }

    fun actionDismiss() {
        _alertDialogUiState.update {
            it.copy(
                show = false,
            )
        }
        internalAlertRepository.endEvent(AlertEndEvent.DISMISS)
    }

    fun actionConfirm() {
        _alertDialogUiState.update {
            it.copy(
                show = false,
            )
        }
        internalAlertRepository.endEvent(AlertEndEvent.CONFIRM)
    }
}
