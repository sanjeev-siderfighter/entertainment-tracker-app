package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.applayers.domain.categories.repository.ICategoriesRepository
import com.siderfighter.entertainmenttracker.applayers.utils.LOG_TAG
import com.siderfighter.entertainmenttracker.testutils.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAllCategoriesUseCaseTest {

    private val testDispatcherProvider = TestDispatcherProvider()
    private val categoriesRepository = mockk<ICategoriesRepository>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcherProvider.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcherProvider.cancel()
    }

    @Test
    fun `test get all categories usecase`() = runTest {
        coEvery {
            categoriesRepository.getAllCategories()
        } returns flowOf(listOf(Category(id = 1, name = "test")))

        val sut = GetAllCategoriesUseCase(repository = categoriesRepository)

        val flow = sut()

        advanceUntilIdle()

        val result = flow.first()

        println("$LOG_TAG result = $result")
        assertTrue(
            result.categories.size == 1 && result.categories[0].name == "test" && result.categories[0].id == 1L
        )
    }
}