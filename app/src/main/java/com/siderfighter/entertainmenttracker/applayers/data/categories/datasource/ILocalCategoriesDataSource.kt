package com.siderfighter.entertainmenttracker.applayers.data.categories.datasource

import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import kotlinx.coroutines.flow.Flow

interface ILocalCategoriesDataSource {
    suspend fun getAllCategories(): Flow<List<Category>>

    suspend fun getCategoryByName(name: String): Flow<String?>
}