package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

import com.siderfighter.entertainmenttracker.applayers.data.categories.repository.CategoriesRepository
import com.siderfighter.entertainmenttracker.applayers.domain.categories.entity.AddCategoryState
import com.siderfighter.entertainmenttracker.testutils.TestDispatcherProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AddNewCategoryUseCaseTest {

    @MockK(relaxed = true)
    private lateinit var categoriesRepository: CategoriesRepository

    @MockK
    private lateinit var isCategoryPresentUseCase: IsCategoryPresentUseCase

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
    fun `test add new category when category is already present - should return CategoryAlreadyExists`() = runTest {
        coEvery {
            isCategoryPresentUseCase(any())
        } returns flowOf(true)

        val useCase = AddNewCategoryUseCase(categoriesRepository, isCategoryPresentUseCase)

        val resultFlow = useCase(categoryName = "test")

        advanceUntilIdle()

        val result = resultFlow.firstOrNull()
        Assert.assertTrue(result == AddCategoryState.CategoryAlreadyExists)
    }

    @Test
    fun `test add new category when category is not present and added successfully - should return Success`() = runTest {
        coEvery {
            isCategoryPresentUseCase(any())
        } returns flowOf(false)

        coEvery {
            categoriesRepository.addNewCategory(any())
        } returns flowOf(1L)

        val useCase = AddNewCategoryUseCase(categoriesRepository, isCategoryPresentUseCase)

        val resultFlow = useCase(categoryName = "test")

        advanceUntilIdle()

        val result = resultFlow.firstOrNull()
        println("starWanderer -> result = $result")
        Assert.assertTrue(result == AddCategoryState.Success)
    }

    @Test
    fun `test add new category when category is not present and not added successfully - should return FailedToAddCategory`() =
        runTest {
            coEvery {
                isCategoryPresentUseCase(any())
            } returns flowOf(false)

            coEvery {
                categoriesRepository.addNewCategory(any())
            } returns flowOf(null)

            val useCase = AddNewCategoryUseCase(categoriesRepository, isCategoryPresentUseCase)

            val resultFlow = useCase(categoryName = "test")

            advanceUntilIdle()

            val result = resultFlow.firstOrNull()
            println("starWanderer -> result = $result")
            Assert.assertTrue(result == AddCategoryState.FailedToAddCategory)
        }
}