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
    fun provideEntertainmentTrackerDb(@ApplicationContext context: Context): EntertainmentTrackerDb {
        return EntertainmentTrackerDb.getInstance(context)
    }

    @Provides
    fun provideEntertainmentTrackerDao(db: EntertainmentTrackerDb): EntertainmentTrackerDao {
        return db.entertainmentTrackerDao()
    }
}