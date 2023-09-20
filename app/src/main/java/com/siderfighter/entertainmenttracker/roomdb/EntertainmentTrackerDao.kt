package com.siderfighter.entertainmenttracker.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category

@Dao
interface EntertainmentTrackerDao {

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>

    @Query("SELECT name FROM category WHERE name IS :name")
    suspend fun getCategoryByName(name: String): String?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addCategory(category: Category): Long?
}