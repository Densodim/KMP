package com.example.testkmpapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsItemEntity(
    val author: String?,
    val title: String?,
    val description: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    var isFavorite: Boolean = false
)

fun NewsItemEntity.toItem(): NewsItem = NewsItem(
    source = null,
    author = author,
    title = title,
    description = description,
    url = url,
    publishedAt = publishedAt,
    content = content,
    urlToImage = urlToImage,
    isFavorite = isFavorite
)

fun NewsItem.toEntity(): NewsItemEntity = NewsItemEntity(
    author = author,
    title = title,
    description = description,
    url = url ?: "",
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content,
    isFavorite = isFavorite
)