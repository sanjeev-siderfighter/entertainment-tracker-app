package com.siderfighter.entertainmenttracker.applayers.domain.categories.repository

import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import kotlinx.coroutines.flow.Flow

interface ICategoriesRepository {
    suspend fun getAllCategories(): Flow<List<Category>>
}