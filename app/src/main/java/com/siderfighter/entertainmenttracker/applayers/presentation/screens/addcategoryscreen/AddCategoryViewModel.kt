package com.siderfighter.entertainmenttracker.applayers.presentation.screens.addcategoryscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.AddCategoryState
import com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase.AddNewCategoryUseCase
import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val addNewCategoryUseCase: AddNewCategoryUseCase,
    private val dispatcher: ICoroutineDispatcherProvider
) : ViewModel() {

    private val mAddCategoryFlow = MutableSharedFlow<AddCategoryUiState>(extraBufferCapacity = 1)
    val addCategoryFlow = mAddCategoryFlow.asSharedFlow()

    fun addCategory(categoryName: String) {
        viewModelScope.launch(dispatcher.io) {
            addNewCategoryUseCase(categoryName = categoryName)
                .onStart {
                    mAddCategoryFlow.tryEmit(AddCategoryUiState.Loading)
                }
                .catch {
//                    Log.e(LOG_TAG, "AddCategoryViewModel -> Error while adding new category", it)
                    mAddCategoryFlow.tryEmit(AddCategoryUiState.FailedToAddCategory)
                }
                .collectLatest { result ->
                    mAddCategoryFlow.tryEmit(handleAddCategoryResult(result = result))
                }
        }
    }

    private fun handleAddCategoryResult(result: AddCategoryState): AddCategoryUiState {
        return when (result) {
            AddCategoryState.CategoryAlreadyExists -> AddCategoryUiState.CategoryAlreadyExists

            AddCategoryState.FailedToAddCategory -> AddCategoryUiState.FailedToAddCategory

            AddCategoryState.Success -> AddCategoryUiState.Success
        }
    }

    // validate category name if categoryName is empty return CategoryNameValidationState.Empty, if it contains special characters then return CategoryNameValidationState.Invalid otherwise CategoryNameValidationState.Valid
    fun validateCategoryName(categoryName: String): CategoryNameValidationState {
        return if (categoryName.isEmpty()) CategoryNameValidationState.Empty
        else if (categoryName.contains(Regex("[^a-zA-Z0-9 ]"))) CategoryNameValidationState.Invalid
        else CategoryNameValidationState.Valid
    }
}