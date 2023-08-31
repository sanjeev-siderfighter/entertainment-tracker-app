package com.siderfighter.entertainmenttracker.configs

import kotlinx.coroutines.CoroutineDispatcher

interface ICoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}