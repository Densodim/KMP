package com.example.testkmpapp.presentation.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun NewsListScreen(viewModel: NewsViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    val news by viewModel.news.collectAsState()
    NewsListView(news)
}