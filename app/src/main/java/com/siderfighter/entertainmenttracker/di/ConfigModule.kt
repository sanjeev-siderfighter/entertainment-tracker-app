package com.siderfighter.entertainmenttracker.di

import com.siderfighter.entertainmenttracker.configs.CoroutineDispatcherProvider
import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
abstract class ConfigModule {
    @Binds
    abstract fun provideCoroutineDispatcherProvider(provider: CoroutineDispatcherProvider): ICoroutineDispatcherProvider
}