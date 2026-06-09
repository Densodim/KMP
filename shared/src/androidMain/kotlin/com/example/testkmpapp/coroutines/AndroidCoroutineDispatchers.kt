package com.example.testkmpapp.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

// Android actual: Android/JVM has a real Main dispatcher and an IO dispatcher.
actual val uiDispatcher: CoroutineContext
    get() = Dispatchers.Main

actual val ioDispatcher: CoroutineContext
    get() = Dispatchers.IO
