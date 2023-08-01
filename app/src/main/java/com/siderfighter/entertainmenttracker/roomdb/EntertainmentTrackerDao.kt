package com.siderfighter.entertainmenttracker.roomdb

import androidx.room.Dao
import androidx.room.Query
import com.siderfighter.entertainmenttracker.data.home.entity.Category

@Dao
interface EntertainmentTrackerDao {

    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>
}