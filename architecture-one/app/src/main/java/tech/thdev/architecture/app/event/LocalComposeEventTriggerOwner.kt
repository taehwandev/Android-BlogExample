package tech.thdev.architecture.app.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf

object LocalComposeEventTriggerOwner {

    private val LocalComposition = staticCompositionLocalOf<FlowComposeInteractionTrigger> { noLocalProvidedFor("FlowComposeInteractionTrigger") }

    val current: FlowComposeInteractionTrigger
        @Composable
        get() = LocalComposition.current

    infix fun provides(registerOwner: FlowComposeInteractionTrigger): ProvidedValue<FlowComposeInteractionTrigger> =
        LocalComposition provides registerOwner
}

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
