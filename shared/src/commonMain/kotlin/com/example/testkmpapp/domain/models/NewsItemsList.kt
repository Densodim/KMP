package com.example.testkmpapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemsList(
    val totalResults: Int,
    val articles: List<NewsItem>
)
