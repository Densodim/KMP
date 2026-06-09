package com.example.testkmpapp.presentation

import com.example.testkmpapp.coroutines.uiDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

// Common ViewModel base: it is placed in presentation because it owns UI-facing coroutine scope lifecycle.
// Domain models should stay plain data/business objects and should not know about CoroutineScope.
open class BaseViewModel(
    dispatcher: CoroutineContext = uiDispatcher,
) {
    private val job = SupervisorJob()

    protected val scope: CoroutineScope = CoroutineScope(dispatcher + job)

    open fun onCleared() {
        scope.cancel()
    }
}
