package com.example.testkmpapp.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

// iOS actuals: Main is used for UI work, Default is a safe shared background dispatcher.
actual val uiDispatcher: CoroutineContext
    get() = Dispatchers.Main

actual val ioDispatcher: CoroutineContext
    get() = Dispatchers.Default
