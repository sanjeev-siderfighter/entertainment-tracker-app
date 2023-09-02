package com.siderfighter.entertainmenttracker.data.categories.datasource

import com.siderfighter.entertainmenttracker.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalCategoriesDataSource @Inject constructor(
    private val entertainmentTrackerDao: EntertainmentTrackerDao
) : ILocalCategoriesDataSource {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return flowOf(
            entertainmentTrackerDao.getAllCategories()
        )
    }
}