package com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource

import com.siderfighter.entertainmenttracker.data.home.entity.Category
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao

class LocalHomeDataSource(
    private val entertainmentTrackerDao: EntertainmentTrackerDao
) : ILocalHomeDataSource {
    override suspend fun getAllCategories(): List<Category> {
        return entertainmentTrackerDao.getAllCategories()
    }
}