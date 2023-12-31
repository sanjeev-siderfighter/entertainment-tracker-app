package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.Category
import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.CategoryList
import com.siderfighter.entertainmenttracker.applayers.domain.categories.repository.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: ICategoriesRepository
) {
    suspend operator fun invoke(): Flow<CategoryList> {
        val exception = Exception("finding stacktrace")
        val stackTrace = exception.stackTrace
        println("starWanderer -> stackTrace = ${stackTrace[0]}")
        val categoriesFlow = repository.getAllCategories()
        return categoriesFlow.transform { categories ->
            emit(
                CategoryList(categories = categories.map {
                    Category(id = it.id, name = it.name)
                })
            )
        }
    }
}