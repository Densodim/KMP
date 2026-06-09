package com.example.testkmpapp.coroutines

import kotlin.coroutines.CoroutineContext

// Common declaration: shared code can use these dispatchers without knowing the platform.
expect val uiDispatcher: CoroutineContext

expect val ioDispatcher: CoroutineContext
