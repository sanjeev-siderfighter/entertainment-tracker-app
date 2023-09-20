package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

import com.siderfighter.entertainmenttracker.applayers.domain.categories.repository.ICategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class IsCategoryPresentUseCase @Inject constructor(
    private val repository: ICategoriesRepository
) {
    suspend operator fun invoke(categoryName: String): Flow<Boolean> {
        val categoryFlow = repository.getCategoryByName(name = categoryName)
        return categoryFlow.transform { category ->
            emit(category == categoryName)
        }
//        return categoryFlow.map { category ->
//            category == categoryName
//        }
    }
}