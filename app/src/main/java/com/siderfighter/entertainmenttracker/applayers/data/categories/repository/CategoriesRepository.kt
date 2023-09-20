package com.siderfighter.entertainmenttracker.applayers.data.categories.repository

import com.siderfighter.entertainmenttracker.applayers.data.categories.datasource.ILocalCategoriesDataSource
import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.applayers.domain.categories.repository.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val localCategoriesDataSource: ILocalCategoriesDataSource
) : ICategoriesRepository {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return localCategoriesDataSource.getAllCategories()
    }

    override suspend fun getCategoryByName(name: String): Flow<String?> {
        return localCategoriesDataSource.getCategoryByName(name = name)
    }

    override suspend fun addNewCategory(name: String): Flow<Long?> {
        return localCategoriesDataSource.addNewCategory(name = name)
    }

}