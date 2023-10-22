package com.siderfighter.entertainmenttracker.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface EntertainmentTrackerDao {

    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT name FROM category WHERE name IS :name")
    fun getCategoryByName(name: String): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
//    @Upsert
    fun addCategory(category: Category): Long?
}