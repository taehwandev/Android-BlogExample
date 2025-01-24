package tech.thdev.architecture.app.common.alert.model

import androidx.compose.runtime.Immutable

@Immutable
data class AlertDialogUiState(
    val show: Boolean,
    val title: String,
    val message: String,
    val confirmButtonText: String,
    val dismissButtonText: String,
) {

    companion object {

        val Default = AlertDialogUiState(
            show = false,
            title = "",
            message = "",
            confirmButtonText = "",
            dismissButtonText = "",
        )
    }
}
