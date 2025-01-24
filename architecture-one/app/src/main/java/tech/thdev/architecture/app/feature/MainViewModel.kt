package tech.thdev.architecture.app.feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.thdev.architecture.app.event.FlowInteraction

@HiltViewModel
class MainViewModel(
    private val flowInteraction: FlowInteraction,
) : ViewModel() {

}
