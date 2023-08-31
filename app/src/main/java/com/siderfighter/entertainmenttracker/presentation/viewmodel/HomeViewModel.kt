package com.siderfighter.entertainmenttracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import com.siderfighter.entertainmenttracker.domain.home.usecase.GetAllCategoriesUseCase
import com.siderfighter.entertainmenttracker.utils.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val dipatcher: ICoroutineDispatcherProvider
) : ViewModel() {

    fun getAllCategories() {
        viewModelScope.launch(dipatcher.io) {
            val allCategories = getAllCategoriesUseCase()
            println("$LOG_TAG -> categories = $allCategories")
        }
    }
}