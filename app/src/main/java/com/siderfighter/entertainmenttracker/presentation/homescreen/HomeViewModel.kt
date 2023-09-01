package com.siderfighter.entertainmenttracker.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import com.siderfighter.entertainmenttracker.domain.home.usecase.GetAllCategoriesUseCase
import com.siderfighter.entertainmenttracker.utils.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val dipatcher: ICoroutineDispatcherProvider
) : ViewModel() {

    private val mCategoriesFlow = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val categoriesFlow = mCategoriesFlow.asStateFlow()

    fun getAllCategories() {
        viewModelScope.launch(dipatcher.io) {
            getAllCategoriesUseCase()
                .onStart {
                    mCategoriesFlow.value = HomeUiState.Loading
                }
                .catch {
                    println("$LOG_TAG -> Error in categoriesFlow -> $it")
                    mCategoriesFlow.value = HomeUiState.Error(throwable = it)
                }
                .collectLatest { categoriesList ->
                    mCategoriesFlow.value = if (categoriesList.categories.isEmpty()) {
                        HomeUiState.NoData
                    } else {
                        HomeUiState.Success(categoryList = categoriesList)
                    }
                    println("$LOG_TAG -> categories = ${categoriesFlow.value}")
                }
        }
    }
}