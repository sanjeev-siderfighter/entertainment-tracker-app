package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

import com.siderfighter.entertainmenttracker.applayers.data.categories.repository.CategoriesRepository
import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.AddCategoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddNewCategoryUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val isCategoryPresentUseCase: IsCategoryPresentUseCase
) {
    suspend operator fun invoke(categoryName: String): Flow<AddCategoryState> {
        return isCategoryPresentUseCase(categoryName = categoryName)
            .flatMapConcat { isPresent ->
                if (!isPresent) {
                    categoriesRepository.addNewCategory(name = categoryName).map { rowId ->
                        if (rowId != null) {
                            AddCategoryState.Success
                        } else {
                            AddCategoryState.FailedToAddCategory
                        }
                    }
                } else {
                    flowOf(AddCategoryState.CategoryAlreadyExists)
                }
            }
    }
}