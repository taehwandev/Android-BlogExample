package tech.thdev.architecture.app.event

import kotlinx.coroutines.flow.SharedFlow

interface FlowInteractionStream {

    val event: SharedFlow<MainEffect>

    fun nextEvent(event: MainEffect)
}
