package com.siderfighter.entertainmenttracker.domain.home.repository

import com.siderfighter.entertainmenttracker.data.home.entity.Category
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    suspend fun getAllCategories(): Flow<List<Category>>
}