package com.siderfighter.entertainmenttracker.applayers.domain.categories.entity

sealed interface AddCategoryState {
    data object Success : AddCategoryState
    data object CategoryAlreadyExists : AddCategoryState
    data object FailedToAddCategory : AddCategoryState
}