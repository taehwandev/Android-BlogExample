package tech.thdev.architecture.app.alert.repository.api

import kotlinx.coroutines.flow.Flow
import tech.thdev.architecture.app.alert.repository.api.model.AlertEndEvent
import tech.thdev.architecture.app.alert.repository.api.model.AlertItem

interface InternalAlertRepository {

    fun show(): Flow<AlertItem>

    fun endEvent(alertEndEvent: AlertEndEvent)
}
