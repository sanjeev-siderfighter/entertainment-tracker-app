package com.siderfighter.entertainmenttracker.di

import android.content.Context
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {
    @Provides
    fun provideEntertainmentTrackerDao(@ApplicationContext context: Context): EntertainmentTrackerDao {
        return EntertainmentTrackerDb.getInstance(context).entertainmentTrackerDao()
    }
}