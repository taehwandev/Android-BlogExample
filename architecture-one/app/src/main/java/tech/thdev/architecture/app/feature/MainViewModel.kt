package tech.thdev.architecture.app.feature

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import tech.thdev.architecture.app.alert.repository.api.AlertRepository
import tech.thdev.architecture.app.alert.repository.api.model.AlertEndEvent
import tech.thdev.architecture.app.alert.repository.api.model.AlertItem
import tech.thdev.architecture.app.event.FlowInteractionStream
import tech.thdev.architecture.app.event.MainEffect
import tech.thdev.architecture.app.feature.model.MainUiState
import tech.thdev.architecture.app.snackbar.repository.api.SnackbarRepository
import tech.thdev.architecture.app.snackbar.repository.api.model.SnackbarItem
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    flowInteractionStream: FlowInteractionStream,
    private val alertRepository: AlertRepository,
    private val snackbarRepository: SnackbarRepository,
) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState.Default)
    val mainUiState = _mainUiState.asStateFlow()

    private val event = flowInteractionStream.event
        .flatMapLatest {
            eventEffect(it)
        }
        .onEach {
            if (it != MainEffect.None) {
                flowInteractionStream.nextEvent(it)
            }
        }

    fun flowLoad() {
        event
            .launchIn(viewModelScope)
    }

    @VisibleForTesting
    fun eventEffect(event: MainEffect): Flow<MainEffect> =
        when (event) {
            MainEffect.ShowAlert -> {
                alertRepository
                    .awaitShow(
                        item = AlertItem(
                            title = "title",
                            message = "A dialog is a type of modal window that appears in front of app content to provide critical" +
                                    "information, or ask for a decision to be made.",
                            confirmButtonText = "Confirm",
                            dismissButtonText = "Dismiss",
                        )
                    )
                    .onEach { endEvent ->
                        _mainUiState.update {
                            it.copy(
                                buttonEndEvent = endEvent.name,
                            )
                        }
                    }
                    .map { MainEffect.None }
            }

            MainEffect.ShowAlertAndSnackbar -> {
                alertRepository
                    .awaitShow(
                        item = AlertItem(
                            title = "title",
                            message = "A dialog is a type of modal window that appears in front of app content to provide critical" +
                                    "information, or ask for a decision to be made.",
                            confirmButtonText = "Confirm",
                            dismissButtonText = "Dismiss",
                        )
                    )
                    .filter {
                        it == AlertEndEvent.Confirm
                    }
                    .onEach {
                        snackbarRepository.awaitShow(
                            item = SnackbarItem(
                                message = "Confirm case",
                                actionLabel = "",
                            )
                        )
                    }
                    .map { MainEffect.None }
            }

            else -> flowOf(MainEffect.None)
        }

}
