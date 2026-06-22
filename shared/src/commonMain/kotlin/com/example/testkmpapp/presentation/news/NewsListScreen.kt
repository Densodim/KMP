package com.example.testkmpapp.presentation.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.testkmpapp.domain.models.NewsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    onItemClick: (NewsItem) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News") }
            )
        }
    ) { padding ->
        LaunchedEffect(Unit) {
            println("onStart")
            viewModel.fetchData()
        }

        val news by viewModel.news.collectAsState()
        Box(modifier = Modifier.padding(padding)) {
            NewsListView(
                items = news,
                onItemClick = onItemClick,
                onFavoriteClick = { viewModel.onFavoriteClick(it) }
            )
        }
    }
}
