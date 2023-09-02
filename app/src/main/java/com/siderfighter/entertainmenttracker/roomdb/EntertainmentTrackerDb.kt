package com.siderfighter.entertainmenttracker.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.siderfighter.entertainmenttracker.data.categories.entity.Category

@Database(entities = [Category::class], version = 1)
abstract class EntertainmentTrackerDb : RoomDatabase() {
    abstract fun entertainmentTrackerDao(): EntertainmentTrackerDao

    companion object {
        const val DATABASE_NAME = "entertainment_tracker_db"

        fun getInstance(applicationContext: Context): EntertainmentTrackerDb {
            return Room.databaseBuilder(
                context = applicationContext,
                klass = EntertainmentTrackerDb::class.java,
                name = DATABASE_NAME
            ).build()
        }
    }
}