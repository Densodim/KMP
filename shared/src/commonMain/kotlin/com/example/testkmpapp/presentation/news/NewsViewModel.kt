package com.example.testkmpapp.presentation.news

import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import com.example.testkmpapp.domain.models.NewsItem
import com.example.testkmpapp.domain.models.ScreenConfig
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

    private val _uiConfig = MutableStateFlow<ScreenConfig?>(null)
    val uiConfig: StateFlow<ScreenConfig?> = _uiConfig

    fun fetchData() = viewModelScope.launch {
        // Имитируем запрос конфига интерфейса с сервера (SDUI)
        _uiConfig.value = ScreenConfig(
            title = "Daily Digest",
            backgroundColor = "#FAF9F6", // Мягкий кремовый
            appBarColor = "#F0EAD6",      // Цвет "яичная скорлупа"
            showSearch = true
        )

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

    fun onFavoriteClick(item: NewsItem) {
        // Находим нажатую новость в списке и меняем ей статус isFavorite
        _news.value = _news.value.map {
            if (it.title == item.title) {
                it.copy(isFavorite = !it.isFavorite)
            } else {
                it
            }
        }
        println("Favorite toggled for: ${item.title}")
    }
}
