package tech.thdev.architecture.app.event

interface FlowComposeInteractionTrigger {

    fun action(event: MainEffect)
}
