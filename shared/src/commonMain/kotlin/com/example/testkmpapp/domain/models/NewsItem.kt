package com.example.testkmpapp.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsItem(
    val source: Source? = null,
    @SerialName("author") val author: String?,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val urlToImage: String? = null,
    val isFavorite: Boolean = false
)
