package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.CategoryList
import com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase.GetAllCategoriesUseCase
import com.siderfighter.entertainmenttracker.applayers.utils.LOG_TAG
import com.siderfighter.entertainmenttracker.testutils.CATEGORY_LIST_OBJECT
import com.siderfighter.entertainmenttracker.testutils.TestDispatcherProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val testDispatcherProvider = TestDispatcherProvider()
    private val getAllCategoriesUseCase = mockk<GetAllCategoriesUseCase>(relaxed = true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, overrideRecordPrivateCalls = true)
        Dispatchers.setMain(testDispatcherProvider.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcherProvider.cancel()
    }

    @Test
    fun `test initial state should be loading`() {
        coEvery {
            getAllCategoriesUseCase()
        } returns flowOf()

        val sut = HomeViewModel(
            dispatcher = testDispatcherProvider,
            getAllCategoriesUseCase = getAllCategoriesUseCase
        )

        val state = sut.categoriesFlow.value

        println("$LOG_TAG loading state test state = $state")
        assertTrue(state is HomeUiState.Loading)
    }

    @Test
    fun `test failure state should be error`() = runTest {
        coEvery {
            getAllCategoriesUseCase()
        } returns flow {
            throw Exception("test exception")
        }

        val sut = HomeViewModel(
            dispatcher = testDispatcherProvider,
            getAllCategoriesUseCase = getAllCategoriesUseCase
        )

//        sut.getAllCategories()

        advanceUntilIdle()

        val state = sut.categoriesFlow.value
        println("$LOG_TAG error state test state = $state")
        assertTrue(state is HomeUiState.Error)
    }

    @Test
    fun `test success state should be no data`() = runTest {
        coEvery {
            getAllCategoriesUseCase()
        } returns flowOf(CategoryList(listOf()))

        val sut = HomeViewModel(
            dispatcher = testDispatcherProvider,
            getAllCategoriesUseCase = getAllCategoriesUseCase
        )

//        sut.getAllCategories()

        advanceUntilIdle()

        val state = sut.categoriesFlow.value
        println("$LOG_TAG no data state test state = $state")
        assertTrue(state is HomeUiState.NoData)
    }

    @Test
    fun `test success state should be success`() = runTest {
        coEvery {
            getAllCategoriesUseCase()
        } returns flowOf(CATEGORY_LIST_OBJECT)

        val sut = HomeViewModel(
            dispatcher = testDispatcherProvider,
            getAllCategoriesUseCase = getAllCategoriesUseCase
        )

//        sut.getAllCategories()

        advanceUntilIdle()

        val state = sut.categoriesFlow.value
        println("$LOG_TAG success state test state = $state")
        assertTrue(state is HomeUiState.Success && state.categoryList == CATEGORY_LIST_OBJECT)
    }
}