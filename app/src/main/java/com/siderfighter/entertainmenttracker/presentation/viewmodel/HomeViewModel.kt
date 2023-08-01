package com.siderfighter.entertainmenttracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.entertainmenttracker.domain.home.usecase.GetAllCategoriesUseCase
import com.siderfighter.entertainmenttracker.utils.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {

    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val allCategories = getAllCategoriesUseCase()
            println("$LOG_TAG -> categories = $allCategories")
        }
    }
}