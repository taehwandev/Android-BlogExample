package tech.thdev.architecture.app.common.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SnackbarScreen(
    snackbarHostState: SnackbarHostState,
    snackbarViewModel: SnackbarViewModel = viewModel(),
) {
    val snackbarUiState by snackbarViewModel.snackbarUiState.collectAsStateWithLifecycle()

    LaunchedEffect(snackbarUiState) {
        if (snackbarUiState != null) {
            val result = snackbarHostState
                .showSnackbar(
                    message = snackbarUiState!!.message,
                    actionLabel = snackbarUiState!!.actionLabel.takeIf { it.isNotEmpty() },
                    duration = SnackbarDuration.Short,
                )

            when (result) {
                SnackbarResult.ActionPerformed -> snackbarViewModel.actionPerformed()
                SnackbarResult.Dismissed -> snackbarViewModel.actionDismiss()
            }
        }
    }

    LaunchedEffect(Unit) {
        snackbarViewModel.load()
    }
}
