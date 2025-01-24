package tech.thdev.architecture.app.common.alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import tech.thdev.architecture.app.alert.repository.api.InternalAlertRepository
import tech.thdev.architecture.app.alert.repository.api.model.AlertEndEvent
import tech.thdev.architecture.app.common.alert.model.AlertDialogUiState
import javax.inject.Inject

@HiltViewModel
class AlertDialogViewModel @Inject constructor(
    private val internalAlertRepository: InternalAlertRepository,
) : ViewModel() {

    private val _alertDialogUiState = MutableStateFlow<AlertDialogUiState?>(null)
    val alertDialogUiState = _alertDialogUiState.asStateFlow()

    private val flowShow = internalAlertRepository.show()
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
        flowShow
            .launchIn(viewModelScope)
    }

    fun actionDismiss() {
        _alertDialogUiState.value = null
        internalAlertRepository.endEvent(AlertEndEvent.Dismiss)
    }

    fun actionConfirm() {
        _alertDialogUiState.value = null
        internalAlertRepository.endEvent(AlertEndEvent.Confirm)
    }
}
