package tech.thdev.architecture.app.event.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.thdev.architecture.app.event.FlowComposeInteractionTrigger
import tech.thdev.architecture.app.event.FlowInteraction
import tech.thdev.architecture.app.event.FlowInteractionStream
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EventModule {

    @Binds
    @Singleton
    fun bindFlowInteractionStream(
        flowInteraction: FlowInteraction,
    ): FlowInteractionStream

    @Binds
    @Singleton
    fun bindFlowComposeInteractionTrigger(
        flowInteraction: FlowInteraction,
    ): FlowComposeInteractionTrigger
}
