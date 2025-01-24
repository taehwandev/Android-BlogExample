package tech.thdev.architecture.app.event.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tech.thdev.architecture.app.event.FlowComposeInteractionTrigger
import tech.thdev.architecture.app.event.FlowInteraction
import tech.thdev.architecture.app.event.FlowInteractionStream

@Module
@InstallIn(ActivityComponent::class)
interface EventModule {

    @Binds
    @Reusable
    fun bindFlowInteractionStream(
        flowInteraction: FlowInteraction,
    ): FlowInteractionStream

    @Binds
    @Reusable
    fun bindFlowComposeInteractionTrigger(
        flowInteraction: FlowInteraction,
    ): FlowComposeInteractionTrigger
}
