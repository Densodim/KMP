package com.example.testkmpapp.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

// Common ViewModel base: it is placed in presentation because it owns UI-facing coroutine scope lifecycle.
// Domain models should stay plain data/business objects and should not know about CoroutineScope.
open class BaseViewModel() {
    protected val scope: CoroutineScope = CoroutineScope(com.example.testkmpapp.util.uiDispatcher)

    open fun onCleared() {
        scope.cancel()
    }
}
