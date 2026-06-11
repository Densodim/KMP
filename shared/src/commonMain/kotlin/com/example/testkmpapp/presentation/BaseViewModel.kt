package com.example.testkmpapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// Common ViewModel base: it is placed in presentation because it owns UI-facing coroutine scope lifecycle.
// Domain models should stay plain data/business objects and should not know about CoroutineScope.
open class BaseViewModel: ViewModel() {
    val scope = this.viewModelScope
}
