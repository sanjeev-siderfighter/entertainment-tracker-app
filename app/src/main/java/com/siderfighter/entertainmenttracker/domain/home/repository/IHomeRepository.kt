package com.siderfighter.entertainmenttracker.domain.home.repository

import com.siderfighter.entertainmenttracker.data.home.entity.Category

interface IHomeRepository {
    suspend fun getAllCategories(): List<Category>
}