package tech.thdev.architecture.app.snackbar.repository

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import tech.thdev.architecture.app.snackbar.repository.api.InternalSnackbarRepository
import tech.thdev.architecture.app.snackbar.repository.api.SnackbarRepository
import tech.thdev.architecture.app.snackbar.repository.api.model.SnackbarItem
import tech.thdev.architecture.app.snackbar.repository.api.model.SnackbarEndEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackbarRepositoryImpl @Inject constructor() : InternalSnackbarRepository, SnackbarRepository {

    @VisibleForTesting
    val channelShow = Channel<SnackbarItem>(Channel.BUFFERED)

    @VisibleForTesting
    val channelEndEvent = Channel<SnackbarEndEvent>(Channel.BUFFERED)

    override fun show(): Flow<SnackbarItem> =
        channelShow.receiveAsFlow()

    override fun awaitShow(item: SnackbarItem): Flow<SnackbarEndEvent> {
        channelShow.trySend(item)
        return channelEndEvent.receiveAsFlow()
    }

    override fun endEvent(endEvent: SnackbarEndEvent) {
        channelEndEvent.trySend(endEvent)
    }
}
