package com.example.testkmpapp.navigation

import androidx.compose.runtime.Composable
import com.example.testkmpapp.presentation.news.NewsViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.viewmodel.ViewModel
import kotlin.reflect.KClass

object ViewModelFactory {
    @Composable
    fun resolve(screen: Screens): NewsViewModel {
        return when (screen) {
            Screens.NewsList -> koinViewModel(NewsViewModel::class)
            Screens.NewsDetails -> koinViewModel(NewsViewModel::class) // Если для деталей нужна та же или другая VM
        }
    }
}
