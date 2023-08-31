package com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource

import com.siderfighter.entertainmenttracker.data.home.entity.Category
import kotlinx.coroutines.flow.Flow

interface ILocalHomeDataSource {
    suspend fun getAllCategories(): Flow<List<Category>>
}