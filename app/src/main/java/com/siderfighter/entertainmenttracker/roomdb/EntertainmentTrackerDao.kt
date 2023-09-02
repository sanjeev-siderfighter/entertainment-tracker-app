package com.siderfighter.entertainmenttracker.roomdb

import androidx.room.Dao
import androidx.room.Query
import com.siderfighter.entertainmenttracker.data.categories.entity.Category

@Dao
interface EntertainmentTrackerDao {

    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>
}