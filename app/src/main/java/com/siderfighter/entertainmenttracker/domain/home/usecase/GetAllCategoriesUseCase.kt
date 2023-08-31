package com.siderfighter.entertainmenttracker.domain.home.usecase

import com.siderfighter.entertainmenttracker.domain.home.entity.Category
import com.siderfighter.entertainmenttracker.domain.home.entity.CategoryList
import com.siderfighter.entertainmenttracker.domain.home.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: IHomeRepository
) {
    suspend operator fun invoke(): Flow<CategoryList> {
        val categoriesFlow = repository.getAllCategories()
        return categoriesFlow.transform { categories ->
            categories.map {
                Category(id = it.id, name = it.name)
            }
        }
    }
}