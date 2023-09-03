package com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase.IsCategoryPresentUseCase
import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val isCategoryPresentUseCase: IsCategoryPresentUseCase,
    private val dispatcher: ICoroutineDispatcherProvider
): ViewModel() {

    private val mIsCategoryPresent = MutableStateFlow(true)
    val isCategoryPresent = mIsCategoryPresent.asStateFlow()

    fun isCategoryPresent(categoryName: String) {
        viewModelScope.launch(dispatcher.io) {
            isCategoryPresentUseCase(categoryName = categoryName)
                .collectLatest { isPresent ->
                    mIsCategoryPresent.value = isPresent
                }
        }
    }
}