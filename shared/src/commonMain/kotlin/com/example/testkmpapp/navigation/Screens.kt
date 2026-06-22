package com.example.testkmpapp.navigation

enum class Screens(val route: String, val title: String) {
    NewsList("/news", "News"),
    NewsDetails("/details/{item}", "Details")
}
