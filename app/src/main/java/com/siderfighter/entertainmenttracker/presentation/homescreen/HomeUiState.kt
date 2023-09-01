package com.siderfighter.entertainmenttracker.presentation.homescreen

import com.siderfighter.entertainmenttracker.domain.home.entity.CategoryList

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val throwable: Throwable) : HomeUiState
    data object NoData : HomeUiState
    data class Success(val categoryList: CategoryList) : HomeUiState
}