package com.example.testkmpapp.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkmpapp.di.DI
import com.example.testkmpapp.domain.models.NewsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val newsUseCase = DI.getNewsUseCase()

    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())
    val news: StateFlow<List<NewsItem>> = _news

    fun fetchData() = viewModelScope.launch {
        val result = newsUseCase.invoke(Unit)
        result.onSuccess {
            _news.value = it?.articles.orEmpty()
        }
    }

    fun loadNews() = viewModelScope.launch {
        val result = newsUseCase.invoke(Unit)
        result.onSuccess {
            _news.value = it?.articles.orEmpty()
        }
    }
}
