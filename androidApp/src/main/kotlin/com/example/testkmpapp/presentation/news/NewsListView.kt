package com.example.testkmpapp.presentation.news


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.testkmpapp.domain.models.NewsItem

@Composable
fun NewsListView(
    items: List<NewsItem>,
    onFavoriteClick: (NewsItem) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) { item ->
            NewsListItemView(
                item = item,
                onFavoriteClick = { onFavoriteClick(item) }
            )
        }
    }
}