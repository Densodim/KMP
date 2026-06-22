package com.example.testkmpapp.navigation

enum class Screens(val route: String) {
    NewsList("/news"),
    NewsDetails("/details/{item}")
}
