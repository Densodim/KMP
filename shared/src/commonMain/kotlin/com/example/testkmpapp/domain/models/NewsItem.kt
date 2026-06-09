package com.example.testkmpapp.domain.models

import kotlinx.serialization.SerialName

data class NewsItem(
    val source: Source? = null,
    @SerialName("author") val author: String?,
    val title: String,
    val description: String,
    val url: String? = null,
    val publishedAt: String? = null,
    val content: String,
    val urlToImage: String? = null
)
