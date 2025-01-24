package tech.thdev.architecture.app.event

sealed interface MainEffect {

    data object ShowAlert : MainEffect

    data object ShowAlertAndSnackbar : MainEffect

    data object None : MainEffect
}
