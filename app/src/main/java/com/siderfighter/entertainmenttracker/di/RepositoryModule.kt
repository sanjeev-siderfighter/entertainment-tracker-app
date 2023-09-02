package com.siderfighter.entertainmenttracker.di

import com.siderfighter.entertainmenttracker.applayers.data.categories.datasource.ILocalCategoriesDataSource
import com.siderfighter.entertainmenttracker.applayers.data.categories.datasource.LocalCategoriesDataSource
import com.siderfighter.entertainmenttracker.applayers.data.categories.repository.CategoriesRepository
import com.siderfighter.entertainmenttracker.applayers.domain.categories.repository.ICategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class, ActivityComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideLocalHomeDataSource(localHomeDataSource: LocalCategoriesDataSource): ILocalCategoriesDataSource

    @Binds
    abstract fun providesHomeRepository(homeRepository: CategoriesRepository): ICategoriesRepository
}