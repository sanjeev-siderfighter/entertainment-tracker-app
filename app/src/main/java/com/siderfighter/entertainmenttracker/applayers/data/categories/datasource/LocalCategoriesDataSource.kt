package com.siderfighter.entertainmenttracker.applayers.data.categories.datasource

import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalCategoriesDataSource @Inject constructor(
    private val entertainmentTrackerDao: EntertainmentTrackerDao,
    private val dispatcher: ICoroutineDispatcherProvider
) : ILocalCategoriesDataSource {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return flowOf(
            entertainmentTrackerDao.getAllCategories()
        ).flowOn(dispatcher.io)
    }

    override suspend fun getCategoryByName(name: String): Flow<String?> {
        return flowOf(
            entertainmentTrackerDao.getCategoryByName(name = name)
        ).flowOn(dispatcher.io)
    }

    override suspend fun addNewCategory(name: String): Flow<Long?> {
        val category = Category(name = name)
        return flowOf(
            entertainmentTrackerDao.addCategory(category = category)
        )
    }
}