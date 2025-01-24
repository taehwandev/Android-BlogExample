package tech.thdev.architecture.app.common.alert

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.thdev.architecture.app.common.alert.model.AlertDialogUiState

@Composable
fun AlertDialogScreen(
    alertDialogViewModel: AlertDialogViewModel = viewModel(),
) {
    val alertDialogUiState by alertDialogViewModel.alertDialogUiState.collectAsStateWithLifecycle()

    alertDialogUiState?.let {
        AlertDialogScreen(
            alertDialogUiState = it,
            onDismissRequest = alertDialogViewModel::actionDismiss,
            onConfirmation = alertDialogViewModel::actionConfirm,
        )
    }

    LaunchedEffect(Unit) {
        alertDialogViewModel.load()
    }
}

@Composable
private fun AlertDialogScreen(
    alertDialogUiState: AlertDialogUiState,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            if (alertDialogUiState.title.isNotEmpty()) {
                Text(text = alertDialogUiState.title)
            }
        },
        text = {
            if (alertDialogUiState.message.isNotEmpty()) {
                Text(text = alertDialogUiState.message)
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            if (alertDialogUiState.confirmButtonText.isNotEmpty()) {
                TextButton(
                    onClick = onConfirmation,
                ) {
                    Text(alertDialogUiState.confirmButtonText)
                }
            }
        },
        dismissButton = {
            if (alertDialogUiState.dismissButtonText.isNotEmpty()) {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(alertDialogUiState.dismissButtonText)
                }
            }
        }
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewAlertDialogScreen() {
    AlertDialogScreen(
        alertDialogUiState = AlertDialogUiState.Default.copy(
            title = "title",
            message = "A dialog is a type of modal window that appears in front of app content to provide critical" +
                    "information, or ask for a decision to be made.",
            confirmButtonText = "Confirm",
            dismissButtonText = "Dismiss",
        ),
        onDismissRequest = {},
        onConfirmation = {},
    )
}
