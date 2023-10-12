package com.siderfighter.entertainmenttracker.applayers.presentation.screens.addcategory

sealed interface AddCategoryUiState {
    data object Loading : AddCategoryUiState
    data object CategoryAlreadyExists : AddCategoryUiState
    data object FailedToAddCategory : AddCategoryUiState
    data object Success : AddCategoryUiState
}

sealed interface CategoryNameValidationState {
    data object Invalid : CategoryNameValidationState
    data object Valid : CategoryNameValidationState
    data object Empty : CategoryNameValidationState
}