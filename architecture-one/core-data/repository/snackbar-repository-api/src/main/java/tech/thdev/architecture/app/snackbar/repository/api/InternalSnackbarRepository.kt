package tech.thdev.architecture.app.snackbar.repository.api

import kotlinx.coroutines.flow.Flow
import tech.thdev.architecture.app.snackbar.repository.api.model.SnackbarItem
import tech.thdev.architecture.app.snackbar.repository.api.model.SnackbarEndEvent

interface InternalSnackbarRepository {

    fun show(): Flow<SnackbarItem>

    fun endEvent(endEvent: SnackbarEndEvent)
}
