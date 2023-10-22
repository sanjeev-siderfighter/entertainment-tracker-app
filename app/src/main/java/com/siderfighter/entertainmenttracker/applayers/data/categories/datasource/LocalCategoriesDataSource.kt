package com.siderfighter.entertainmenttracker.applayers.data.categories.datasource

import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalCategoriesDataSource @Inject constructor(
    private val entertainmentTrackerDao: EntertainmentTrackerDao
) : ILocalCategoriesDataSource {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return entertainmentTrackerDao.getAllCategories()
    }

    override suspend fun getCategoryByName(name: String): Flow<String?> {
        return entertainmentTrackerDao.getCategoryByName(name = name)
    }

    override suspend fun addNewCategory(name: String): Flow<Long?> {
        val category = Category(name = name)
        return flowOf(
            entertainmentTrackerDao.addCategory(category = category)
        )
    }
}