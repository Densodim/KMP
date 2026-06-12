package com.example.testkmpapp.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkmpapp.domain.models.NewsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.testkmpapp.api.network.NewsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewsViewModel : ViewModel(), KoinComponent {
    private val newsUseCase: NewsUseCase by inject()

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
