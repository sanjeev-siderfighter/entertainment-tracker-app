package com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource

import com.siderfighter.entertainmenttracker.data.home.entity.Category

interface ILocalHomeDataSource {
    suspend fun getAllCategories(): List<Category>
}