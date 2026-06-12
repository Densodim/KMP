package com.example.testkmpapp.domain.models

import com.example.testkmpapp.api.network.NewsUseCase
import com.example.testkmpapp.presentation.BaseViewModel
import com.example.testkmpapp.util.asCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class NewsViewModels : BaseViewModel(), KoinComponent {
    private val useCase: NewsUseCase by inject()
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
