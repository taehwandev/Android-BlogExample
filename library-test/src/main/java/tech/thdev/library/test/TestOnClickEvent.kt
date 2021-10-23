package tech.thdev.library.test

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import tech.thdev.library.OnClickEventControl

class TestOnClickEvent : OnClickEventControl {

    private val sharedFlow = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun click() =
        sharedFlow.tryEmit(true)

    override fun onClick(resourceId: Int): Flow<Boolean> =
        sharedFlow
}