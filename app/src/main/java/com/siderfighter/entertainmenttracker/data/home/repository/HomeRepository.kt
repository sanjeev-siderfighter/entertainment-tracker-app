package com.siderfighter.entertainmenttracker.data.home.repository

import com.siderfighter.entertainmenttracker.data.home.datasource.localdatasource.ILocalHomeDataSource
import com.siderfighter.entertainmenttracker.data.home.entity.Category
import com.siderfighter.entertainmenttracker.domain.home.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val localHomeDataSource: ILocalHomeDataSource
) : IHomeRepository {
    override suspend fun getAllCategories(): Flow<List<Category>> {
        return localHomeDataSource.getAllCategories()
    }

}