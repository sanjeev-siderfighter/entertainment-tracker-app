package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

import com.siderfighter.entertainmenttracker.applayers.data.categories.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddNewCategoryUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val isCategoryPresentUseCase: IsCategoryPresentUseCase
) {
    suspend operator fun invoke(categoryName: String): Flow<Boolean> {
        return isCategoryPresentUseCase(categoryName = categoryName)
            .flatMapConcat { isPresent ->
                if (!isPresent) {
                    categoriesRepository.addNewCategory(name = categoryName).map { rowId ->
                        rowId != null
                    }
                } else {
                    flowOf(false)
                }
            }
    }
}