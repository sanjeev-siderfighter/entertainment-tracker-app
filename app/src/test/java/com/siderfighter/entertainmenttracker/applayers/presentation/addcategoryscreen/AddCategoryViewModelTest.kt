package com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen

import app.cash.turbine.test
import app.cash.turbine.testIn
import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.AddCategoryState
import com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase.AddNewCategoryUseCase
import com.siderfighter.entertainmenttracker.applayers.presentation.screens.addcategoryscreen.AddCategoryUiState
import com.siderfighter.entertainmenttracker.applayers.presentation.screens.addcategoryscreen.AddCategoryViewModel
import com.siderfighter.entertainmenttracker.testutils.TestDispatcherProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AddCategoryViewModelTest {

    @MockK
    private lateinit var addNewCategoryUseCase: AddNewCategoryUseCase

    private val testDispatcherProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcherProvider.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `test add category for loading state`() = runTest {
        coEvery {
            addNewCategoryUseCase(any())
        } returns flowOf()

        val sut = AddCategoryViewModel(addNewCategoryUseCase, testDispatcherProvider)

        sut.addCategory("test")

        sut.addCategoryFlow.test {
            val result = awaitItem()
            println("starWanderer -> loading test result = $result")
            Assert.assertTrue(result is AddCategoryUiState.Loading)
        }
    }

    @Test
    fun `test add category for success state`() = runTest {
        coEvery {
            addNewCategoryUseCase(any())
        } returns flowOf(AddCategoryState.Success)

        val sut = AddCategoryViewModel(addNewCategoryUseCase, testDispatcherProvider)

        sut.addCategory("test")

        sut.addCategoryFlow.test {
            val resultLoading = awaitItem()
            println("starWanderer -> success test result = $resultLoading")
            Assert.assertTrue(resultLoading is AddCategoryUiState.Loading)

            val resultSuccess = awaitItem()
            println("starWanderer -> success test result = $resultSuccess")
            Assert.assertTrue(resultSuccess is AddCategoryUiState.Success)
        }
    }

    @Test
    fun `test add category for error state`() = runTest {
        coEvery {
            addNewCategoryUseCase(any())
        } returns flow {
            throw Exception("test exception")
        }


        val sut = AddCategoryViewModel(addNewCategoryUseCase, testDispatcherProvider)

        val resultList = mutableListOf<AddCategoryUiState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcherProvider.testDispatcher.scheduler)) {
            sut.addCategoryFlow.toList(resultList)
        }

        sut.addCategory("test")
        advanceUntilIdle()

        println("starWanderer -> test resultList = $resultList")
        val resultLoading = resultList.first()
        val resultError = resultList.last()
        Assert.assertTrue(resultLoading is AddCategoryUiState.Loading && resultError is AddCategoryUiState.FailedToAddCategory)
    }

    @Test
    fun `test add category for adding failed state`() = runTest {
        coEvery {
            addNewCategoryUseCase(any())
        } returns flowOf(AddCategoryState.FailedToAddCategory)

        val sut = AddCategoryViewModel(addNewCategoryUseCase, testDispatcherProvider)

        sut.addCategory("test")

        sut.addCategoryFlow.test {
            val resultLoading = awaitItem()
            println("starWanderer -> success test result = $resultLoading")
            Assert.assertTrue(resultLoading is AddCategoryUiState.Loading)

            val resultSuccess = awaitItem()
            println("starWanderer -> success test result = $resultSuccess")
            Assert.assertTrue(resultSuccess is AddCategoryUiState.FailedToAddCategory)
        }
    }

    @Test
    fun `test add category for category already exist state`() = runTest {
        coEvery {
            addNewCategoryUseCase(any())
        } returns flowOf(AddCategoryState.CategoryAlreadyExists)

        val sut = AddCategoryViewModel(addNewCategoryUseCase, testDispatcherProvider)

        sut.addCategory("test")

        sut.addCategoryFlow.test {
            val resultLoading = awaitItem()
            println("starWanderer -> success test result = $resultLoading")
            Assert.assertTrue(resultLoading is AddCategoryUiState.Loading)

            val resultSuccess = awaitItem()
            println("starWanderer -> success test result = $resultSuccess")
            Assert.assertTrue(resultSuccess is AddCategoryUiState.CategoryAlreadyExists)
        }
    }
}