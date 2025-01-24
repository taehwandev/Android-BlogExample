package tech.thdev.architecture.app.alert.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.thdev.architecture.app.alert.repository.AlertRepositoryImpl
import tech.thdev.architecture.app.alert.repository.api.AlertRepository
import tech.thdev.architecture.app.alert.repository.api.InternalAlertRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AlertRepositoryModel {

    @Binds
    @Singleton
    abstract fun provideInternalAlertRepository(
        alertRepository: AlertRepositoryImpl,
    ): InternalAlertRepository

    @Binds
    @Singleton
    abstract fun provideAlertRepository(
        alertRepository: AlertRepositoryImpl,
    ): AlertRepository
}
