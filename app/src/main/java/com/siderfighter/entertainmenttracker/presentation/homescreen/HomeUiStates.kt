package com.siderfighter.entertainmenttracker.presentation.homescreen

import com.siderfighter.entertainmenttracker.domain.home.entity.CategoryList

sealed interface HomeUiStates {
    data object Loading : HomeUiStates
    data class Error(val throwable: Throwable) : HomeUiStates
    data object NoData : HomeUiStates
    data class Success(val categoryList: CategoryList) : HomeUiStates
}