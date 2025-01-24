package tech.thdev.architecture.app.common.snackbar.model

import androidx.compose.runtime.Immutable

@Immutable
data class SnackbarUiState(
    val message: String,
    val actionLabel: String,
)
