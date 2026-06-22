package com.example.testkmpapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ScreenConfig(
    val title: String,
    val backgroundColor: String, // Hex format like #F5F5F5
    val appBarColor: String,
    val showSearch: Boolean = false
)
