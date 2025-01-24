package tech.thdev.architecture.app.alert.repository.api.model

data class AlertItem(
    val title: String,
    val message: String,
    val confirmButtonText: String,
    val dismissButtonText: String,
)
