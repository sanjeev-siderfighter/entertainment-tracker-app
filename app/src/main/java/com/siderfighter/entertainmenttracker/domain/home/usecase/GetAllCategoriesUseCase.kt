package com.siderfighter.entertainmenttracker.domain.home.usecase

import com.siderfighter.entertainmenttracker.domain.home.entity.Category
import com.siderfighter.entertainmenttracker.domain.home.entity.CategoryList
import com.siderfighter.entertainmenttracker.domain.home.repository.IHomeRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: IHomeRepository
) {
    suspend operator fun invoke(): CategoryList {
        val categories = repository.getAllCategories()
        return CategoryList(categories = categories.map { Category(id = it.id, name = it.name) })
    }
}