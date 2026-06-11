package com.example.testkmpapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Интерфейс для отмены подписки из Swift (iOS)
 */
fun interface Cancellable {
    fun cancel()
}

/**
 * Обертка над Flow для удобного использования в Swift.
 * Позволяет подписываться на поток данных через callback.
 */
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Cancellable {
        val job = Job()
        val scope = CoroutineScope(uiDispatcher + job)
        
        onEach { block(it) }.launchIn(scope)
        
        return Cancellable { job.cancel() }
    }
}

/**
 * Extension-функция для преобразования обычного Flow в CommonFlow
 */
fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)
