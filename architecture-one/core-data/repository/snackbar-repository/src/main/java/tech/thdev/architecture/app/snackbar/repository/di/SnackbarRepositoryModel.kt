package tech.thdev.architecture.app.snackbar.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.thdev.architecture.app.snackbar.repository.SnackbarRepositoryImpl
import tech.thdev.architecture.app.snackbar.repository.api.InternalSnackbarRepository
import tech.thdev.architecture.app.snackbar.repository.api.SnackbarRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SnackbarRepositoryModel {

    @Binds
    @Singleton
    abstract fun provideInternalSnackbarRepository(
        snackbarRepository: SnackbarRepositoryImpl,
    ): InternalSnackbarRepository

    @Binds
    @Singleton
    abstract fun provideSnackbarRepository(
        snackbarRepository: SnackbarRepositoryImpl,
    ): SnackbarRepository
}
