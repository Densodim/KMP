package com.example.testkmpapp.service


import com.example.testkmpapp.util.ioDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class SameService {

    private val job = SupervisorJob()
    val scope: CoroutineScope = CoroutineScope(ioDispatcher + job)

    // Android-only demo: this file is in androidMain, so it may use JVM Thread.currentThread().
    // In commonMain we avoid Thread because iOS/Native does not expose the same API.
    fun printCoroutineInfo() {
        scope.launch(CoroutineName("SameServiceDemo")) {
            println("Coroutine name: ${coroutineContext[CoroutineName]?.name}")
            println("Coroutine context: $coroutineContext")
            println("Thread: ${Thread.currentThread().name}")
        }
    }

    fun clear() {
        job.cancel()
    }
}

