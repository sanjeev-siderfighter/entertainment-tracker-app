package com.siderfighter.entertainmenttracker.data.home.repository

import com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource.ILocalHomeDataSource
import com.siderfighter.entertainmenttracker.data.home.entity.Category
import com.siderfighter.entertainmenttracker.domain.home.repository.IHomeRepository

class HomeRepository(
    private val localHomeDataSource: ILocalHomeDataSource
) : IHomeRepository {
    override suspend fun getAllCategories(): List<Category> {
        return localHomeDataSource.getAllCategories()
    }

}