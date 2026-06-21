package com.example.testkmpapp.domain.models

import androidx.lifecycle.viewModelScope
import com.example.testkmpapp.api.network.NewsUseCase
import com.example.testkmpapp.domain.models.room.NewsCachedUseCase
import com.example.testkmpapp.domain.models.room.SaveNewsUseCase
import com.example.testkmpapp.presentation.BaseViewModel
import com.example.testkmpapp.util.asCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class NewsViewModels : BaseViewModel(), KoinComponent {
    private val useCase: NewsUseCase by inject()
    private val loadCase: NewsCachedUseCase by inject ()
    private val saveCase: SaveNewsUseCase by inject()
    private val _newFlow = MutableStateFlow<NewsItemsList?>(null)
    val newFlow = _newFlow.asCommonFlow()

    fun loadNews() {
        viewModelScope.launch {
            // то, что лежит в Room (с сохранёнными флагами isFavorite)
            val cachedItems = loadCase.invoke(Unit).getOrNull().orEmpty()

            val result = useCase.invoke(Unit)
            result.getOrNull()?.let { newsList ->
                val temp = mutableListOf<NewsItem>()

                newsList.articles.reversed().forEach { article ->
                    // ищем эту же новость в кэше по url
                    val cached = cachedItems.find { it.url == article.url }
                    // если новость уже была в кэше — переносим флаг "избранное",
                    // иначе берём статью из сети как есть
                    val item = if (cached != null) {
                        article.copy(isFavorite = cached.isFavorite)
                    } else {
                        article
                    }
                    temp.add(item)
                    // сохраняем/обновляем в базе
                    saveCase.invoke(item)
                }

                _newFlow.value = NewsItemsList(
                    totalResults = temp.size,
                    articles = temp
                )
            } ?: run {
                // сети нет — показываем то, что есть в кэше
                if (cachedItems.isNotEmpty()) {
                    _newFlow.value = NewsItemsList(
                        totalResults = cachedItems.size,
                        articles = cachedItems
                    )
                }
            }
        }
    }

    fun onFavoriteClick(item: NewsItem) {
        viewModelScope.launch {
            val updated = item.copy(isFavorite = !item.isFavorite)

            // обновляем элемент в текущем списке, который показан в UI
            val current = _newFlow.value?.articles.orEmpty()
            val newArticles = current.map { if (it.url == item.url) updated else it }
            _newFlow.value = NewsItemsList(
                totalResults = newArticles.size,
                articles = newArticles
            )

            // сохраняем в базу (saveCase сам уходит на ioDispatcher)
            saveCase.invoke(updated)
        }
    }
}


