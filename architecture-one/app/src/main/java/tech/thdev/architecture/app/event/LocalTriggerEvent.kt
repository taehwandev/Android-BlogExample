package tech.thdev.architecture.app.event

interface LocalTriggerEvent {

    fun onEvent(event: MainEffect)
}
