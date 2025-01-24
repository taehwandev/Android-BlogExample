package tech.thdev.architecture.app.alert.repository

import androidx.annotation.VisibleForTesting
import dagger.Reusable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import tech.thdev.architecture.app.alert.repository.api.AlertRepository
import tech.thdev.architecture.app.alert.repository.api.InternalAlertRepository
import tech.thdev.architecture.app.alert.repository.api.model.AlertEndEvent
import tech.thdev.architecture.app.alert.repository.api.model.AlertItem
import javax.inject.Inject

@Reusable
class AlertRepositoryImpl @Inject constructor() : InternalAlertRepository, AlertRepository {

    @VisibleForTesting
    val channelShow = Channel<AlertItem>(Channel.BUFFERED)

    @VisibleForTesting
    val channelEndEvent = Channel<AlertEndEvent>(Channel.BUFFERED)

    override fun show(): Flow<AlertItem> =
        channelShow.receiveAsFlow()

    override fun awaitShow(item: AlertItem): Flow<AlertEndEvent> {
        channelShow.trySend(item)
        return channelEndEvent.receiveAsFlow()
    }

    override fun endEvent(alertEndEvent: AlertEndEvent) {
        channelEndEvent.trySend(alertEndEvent)
    }
}
