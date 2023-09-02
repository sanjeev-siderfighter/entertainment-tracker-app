package com.siderfighter.entertainmenttracker.testutils

import com.siderfighter.entertainmenttracker.configs.ICoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatcherProvider : ICoroutineDispatcherProvider {

    val testDispatcher = StandardTestDispatcher()
    override val main: CoroutineDispatcher = testDispatcher
    override val io: CoroutineDispatcher = testDispatcher
    override val default: CoroutineDispatcher = testDispatcher

    fun cancel() {
        testDispatcher.cancel()
    }
}