package com.siderfighter.entertainmenttracker.applayers.domain.categories.usecase

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

class IsCategoryPresentUseCaseTest {

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
    fun `test is category present usecase when category is not present`() = runTest {
        coEvery {
            categoriesRepository.getCategoryByName(name = "test")
        } returns flowOf(null)

        val sut = IsCategoryPresentUseCase(categoriesRepository)

        val flow = sut("test")

        advanceUntilIdle()

        val result = flow.first()

        println("$LOG_TAG usecase when category not present -> result = $result")
        assertTrue(!result)
    }

    @Test
    fun `test is category present usecase when category is present`() = runTest {
        coEvery {
            categoriesRepository.getCategoryByName(name = "test")
        } returns flowOf("test")

        val sut = IsCategoryPresentUseCase(categoriesRepository)

        val flow = sut("test")

        advanceUntilIdle()

        val result = flow.first()

        println("$LOG_TAG usecase when category not present -> result = $result")
        assertTrue(result)
    }

    @Test
    fun `test is category present usecase when db category name is different`() = runTest {
        coEvery {
            categoriesRepository.getCategoryByName(name = "test")
        } returns flowOf("test2")

        val sut = IsCategoryPresentUseCase(categoriesRepository)

        val flow = sut("test")

        advanceUntilIdle()

        val result = flow.first()

        println("$LOG_TAG usecase when category not present -> result = $result")
        assertTrue(!result)
    }
}