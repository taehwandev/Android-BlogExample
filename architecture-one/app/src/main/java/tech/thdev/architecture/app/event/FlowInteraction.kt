package tech.thdev.architecture.app.event

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlowInteraction @Inject constructor() : FlowInteractionStream, FlowComposeInteractionTrigger {

    private val _event = MutableSharedFlow<MainEffect>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override val event = _event.asSharedFlow()

    override fun action(event: MainEffect) {
        _event.tryEmit(event)
    }

    override fun nextEvent(event: MainEffect) {
        action(event)
    }
}
