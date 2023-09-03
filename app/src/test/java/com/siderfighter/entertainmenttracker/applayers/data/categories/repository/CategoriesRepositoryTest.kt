package com.siderfighter.entertainmenttracker.applayers.data.categories.repository

import com.siderfighter.entertainmenttracker.applayers.data.categories.datasource.ILocalCategoriesDataSource
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
import org.junit.Before
import org.junit.Test

class CategoriesRepositoryTest {

    private val testDispatcherProvider = TestDispatcherProvider()
    private val localCategoriesDataSource = mockk<ILocalCategoriesDataSource>(relaxed = true)

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
    fun `test get all categories`() = runTest {
        coEvery {
            localCategoriesDataSource.getAllCategories()
        } returns flowOf(listOf())

        val sut = CategoriesRepository(localCategoriesDataSource)
        val flow = sut.getAllCategories()

        advanceUntilIdle()

        val value = flow.first()
        println("$LOG_TAG value = $value")
        assert(value.isEmpty())
    }

    @Test
    fun `test get category by name`() = runTest {
        coEvery {
            localCategoriesDataSource.getCategoryByName("test")
        } returns flowOf(null)

        val sut = CategoriesRepository(localCategoriesDataSource)

        val flow = sut.getCategoryByName(name = "test")

        advanceUntilIdle()

        val value = flow.first()
        println("$LOG_TAG test get category by name -> value = $value")
        assert(value == null)
    }
}