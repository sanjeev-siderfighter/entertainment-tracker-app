package com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource

import com.siderfighter.entertainmenttracker.data.home.entity.Category
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalHomeDataSource @Inject constructor(
    private val entertainmentTrackerDao: EntertainmentTrackerDao
) : ILocalHomeDataSource {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return flowOf(
            entertainmentTrackerDao.getAllCategories()
        )
    }
}