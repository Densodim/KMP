package com.example.testkmpapp.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.testkmpapp.domain.models.NewsItem
import com.example.testkmpapp.utils.toColor

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    onItemClick: (NewsItem) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    val news by viewModel.news.collectAsState()
    val uiConfig by viewModel.uiConfig.collectAsState()

    // Используем цвет фона из конфига, если он пришел, иначе белый
    val backgroundColor = uiConfig?.backgroundColor?.toColor() ?: Color.White

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        NewsListView(
            items = news,
            onItemClick = onItemClick,
            onFavoriteClick = { viewModel.onFavoriteClick(it) }
        )
    }
}
