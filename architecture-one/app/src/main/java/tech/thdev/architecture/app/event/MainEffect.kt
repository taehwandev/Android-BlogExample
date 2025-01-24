package tech.thdev.architecture.app.event

sealed interface MainEffect {

    data object ShowAlert : MainEffect

    data object None : MainEffect
}
