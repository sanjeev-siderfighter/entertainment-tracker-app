package com.siderfighter.entertainmenttracker.di

import com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource.ILocalHomeDataSource
import com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource.LocalHomeDataSource
import com.siderfighter.entertainmenttracker.data.home.repository.HomeRepository
import com.siderfighter.entertainmenttracker.domain.home.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideLocalHomeDataSource(localHomeDataSource: LocalHomeDataSource): ILocalHomeDataSource

    @Binds
    abstract fun providesHomeRepository(homeRepository: HomeRepository): IHomeRepository
}