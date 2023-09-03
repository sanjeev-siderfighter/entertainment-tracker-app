package com.siderfighter.entertainmenttracker.applayers.data.categories.datasource

import com.siderfighter.entertainmenttracker.applayers.data.categories.entity.Category
import com.siderfighter.entertainmenttracker.applayers.utils.LOG_TAG
import com.siderfighter.entertainmenttracker.roomdb.EntertainmentTrackerDao
import com.siderfighter.entertainmenttracker.testutils.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LocalCategoriesDataSourceTest {

    private val testDispatcherProvider = TestDispatcherProvider()

    //    private val entertainmentTrackerDao = Mockito.mock(EntertainmentTrackerDao::class.java)
    private val entertainmentTrackerDao = mockk<EntertainmentTrackerDao>(relaxed = true)

    @Before
    fun setup() {
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
            entertainmentTrackerDao.getAllCategories()
        } returns emptyList()
//        Mockito.`when`(entertainmentTrackerDao.getAllCategories()).doReturn(listOf())

        val sut = LocalCategoriesDataSource(
            entertainmentTrackerDao = entertainmentTrackerDao,
            dispatcher = testDispatcherProvider
        )

        val flow = sut.getAllCategories()

        advanceUntilIdle()

        val value = flow.first()

        println("$LOG_TAG value = $value")
        assertTrue(value.isEmpty())
    }

    @Test
    fun `test get category by name when category not present`() = runTest {
        coEvery {
            entertainmentTrackerDao.getCategoryByName("test")
        } returns null

        val sut = LocalCategoriesDataSource(
            entertainmentTrackerDao = entertainmentTrackerDao,
            dispatcher = testDispatcherProvider
        )

        val flow = sut.getCategoryByName("test")

        advanceUntilIdle()

        val value = flow.first()

        println("$LOG_TAG test get by name when not prsent -> value = $value")
        assertTrue(value == null)
    }

    @Test
    fun `test get category by name when category is present`() = runTest {
        coEvery {
            entertainmentTrackerDao.getCategoryByName("test")
        } returns "test"

        val sut = LocalCategoriesDataSource(
            entertainmentTrackerDao = entertainmentTrackerDao,
            dispatcher = testDispatcherProvider
        )

        val flow = sut.getCategoryByName("test")

        advanceUntilIdle()

        val value = flow.first()

        println("$LOG_TAG test get by name when not prsent -> value = $value")
        assertTrue(value == "test")
    }
}