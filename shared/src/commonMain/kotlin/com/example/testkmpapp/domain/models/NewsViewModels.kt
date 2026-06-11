package com.example.testkmpapp.domain.models

import com.example.testkmpapp.di.DI
import com.example.testkmpapp.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class NewsViewModels : BaseViewModel() {
    private val useCase = DI.getNewsUseCase()
    val newFlow = MutableStateFlow<NewsItemsList?>(null)

    fun loadNews() {
        scope.launch {
            val result = useCase.invoke(Unit)

            result.getOrNull()?.let {
                newFlow.tryEmit(it)
            }
        }
    }
}
