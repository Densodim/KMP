package com.example.testkmpapp.presentation.news

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testkmpapp.domain.models.NewsItem

@Composable
fun NewsDetailScreen(item: NewsItem, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = item.title.orEmpty(), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.description.orEmpty(), style = MaterialTheme.typography.bodyLarge)
    }
}
