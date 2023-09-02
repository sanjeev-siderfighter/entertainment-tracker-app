package com.siderfighter.entertainmenttracker.data.categories.datasource

import com.siderfighter.entertainmenttracker.data.categories.entity.Category
import kotlinx.coroutines.flow.Flow

interface ILocalCategoriesDataSource {
    suspend fun getAllCategories(): Flow<List<Category>>
}