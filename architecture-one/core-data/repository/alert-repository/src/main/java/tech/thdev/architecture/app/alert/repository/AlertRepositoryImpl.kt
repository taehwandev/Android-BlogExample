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
    val channelAlertShow = Channel<AlertItem>(Channel.BUFFERED)

    @VisibleForTesting
    val channelAlertEndEvent = Channel<AlertEndEvent>(Channel.BUFFERED)

    override fun showAlert(): Flow<AlertItem> =
        channelAlertShow.receiveAsFlow()

    override fun awaitShowAlert(alertItem: AlertItem): Flow<AlertEndEvent> {
        channelAlertShow.trySend(alertItem)
        return channelAlertEndEvent.receiveAsFlow()
    }

    override fun endEvent(alertEndEvent: AlertEndEvent) {
        channelAlertEndEvent.trySend(alertEndEvent)
    }
}
