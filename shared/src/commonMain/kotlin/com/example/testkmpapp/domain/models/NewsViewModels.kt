package com.example.testkmpapp.domain.models

import com.example.testkmpapp.di.DI
import com.example.testkmpapp.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class NewsViewModels() : BaseViewModel() {

    val newFlow = MutableStateFlow<NewsItemsList?>(null)

    private val service = DI.getNewsService()

    fun loadNews(){
        scope.launch {
            
        }
    }
}
