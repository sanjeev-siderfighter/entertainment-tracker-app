package com.siderfighter.entertainmenttracker.domain.categories.repository

import com.siderfighter.entertainmenttracker.data.categories.entity.Category
import kotlinx.coroutines.flow.Flow

interface ICategoriesRepository {
    suspend fun getAllCategories(): Flow<List<Category>>
}