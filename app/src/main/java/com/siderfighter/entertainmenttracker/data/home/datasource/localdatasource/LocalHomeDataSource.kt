package com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource

import com.siderfighter.entertainmenttracker.data.home.entity.Category
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import javax.inject.Inject

class LocalHomeDataSource @Inject constructor(
    private val entertainmentTrackerDao: EntertainmentTrackerDao
) : ILocalHomeDataSource {
    override suspend fun getAllCategories(): List<Category> {
        return entertainmentTrackerDao.getAllCategories()
    }
}