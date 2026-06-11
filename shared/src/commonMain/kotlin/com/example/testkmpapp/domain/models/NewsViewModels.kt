package com.example.testkmpapp.domain.models

import com.example.testkmpapp.di.DI
import com.example.testkmpapp.presentation.BaseViewModel
import com.example.testkmpapp.util.asCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class NewsViewModels : BaseViewModel() {
    private val useCase = DI.getNewsUseCase()
    private val _newFlow = MutableStateFlow<NewsItemsList?>(null)
    val newFlow = _newFlow.asCommonFlow()

    fun loadNews() {
        scope.launch {
            val result = useCase.invoke(Unit)

            result.getOrNull()?.let {
                _newFlow.emit(it)
            }
        }
    }
}
