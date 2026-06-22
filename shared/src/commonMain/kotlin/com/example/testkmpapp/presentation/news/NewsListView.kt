package com.example.testkmpapp.presentation.news


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testkmpapp.domain.models.NewsItem

@Composable
fun NewsListView(
    items: List<NewsItem>,
    onFavoriteClick: (NewsItem) -> Unit,
    onItemClick: (NewsItem) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        items(items) { item ->
            NewsListItemView(
                item = item,
                onFavoriteClick = { onFavoriteClick(item) },
                modifier = Modifier.clickable { onItemClick(item) }
            )
        }
    }
}
