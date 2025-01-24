package tech.thdev.architecture.app.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tech.thdev.architecture.app.event.FlowInteraction
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val flowInteraction: FlowInteraction,
) : ViewModel() {

    private val event = flowInteraction.event
        .onEach {
            Log.d("TEMP", "event! $it")
        }

    fun flowLoad() {
        event
            .launchIn(viewModelScope)
    }

}
