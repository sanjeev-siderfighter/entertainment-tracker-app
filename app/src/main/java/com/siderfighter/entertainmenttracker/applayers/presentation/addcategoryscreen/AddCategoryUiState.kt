package com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen

sealed interface AddCategoryUiState {
    data object Loading : AddCategoryUiState
    data object CategoryAlreadyExists : AddCategoryUiState
    data object FailedToAddCategory : AddCategoryUiState
    data object Success : AddCategoryUiState
}