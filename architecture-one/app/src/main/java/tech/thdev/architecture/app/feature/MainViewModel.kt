package tech.thdev.architecture.app.feature

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import tech.thdev.architecture.app.alert.repository.api.AlertRepository
import tech.thdev.architecture.app.alert.repository.api.model.AlertEndEvent
import tech.thdev.architecture.app.alert.repository.api.model.AlertItem
import tech.thdev.architecture.app.event.FlowInteraction
import tech.thdev.architecture.app.event.MainEffect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    flowInteraction: FlowInteraction,
    private val alertRepository: AlertRepository,
) : ViewModel() {

    private val event = flowInteraction.event
        .flatMapLatest {
            eventEffect(it)
        }
        .onEach {
            if (it != MainEffect.None) {
                Log.d("MainViewModel", "event: $it")
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
                Log.i("TEMP", "showAlert")
                alertRepository
                    .awaitShowAlert(
                        alertItem = AlertItem(
                            title = "제목",
                            message = "여기에 메시지가 뜰거야",
                            confirmButtonText = "확인",
                            dismissButtonText = "취소",
                        )
                    )
                    .filter {
                        it == AlertEndEvent.CONFIRM
                    }
                    .onEach {
                        Log.i("TEMP", "confirm!!!")
                    }
                    .map { MainEffect.None }
            }

            else -> flowOf(MainEffect.None)
        }

}
