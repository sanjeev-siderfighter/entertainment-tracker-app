package com.siderfighter.entertainmenttracker.applayers.presentation.screens.homescreen

import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.CategoryList

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val throwable: Throwable) : HomeUiState
    data object NoData : HomeUiState
    data class Success(val categoryList: CategoryList) : HomeUiState
}