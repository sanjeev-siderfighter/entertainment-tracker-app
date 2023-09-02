package com.siderfighter.entertainmenttracker.data.categories.repository

import com.siderfighter.entertainmenttracker.data.categories.datasource.ILocalCategoriesDataSource
import com.siderfighter.entertainmenttracker.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.domain.categories.repository.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val localHomeDataSource: ILocalCategoriesDataSource
) : ICategoriesRepository {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return localHomeDataSource.getAllCategories()
    }

}