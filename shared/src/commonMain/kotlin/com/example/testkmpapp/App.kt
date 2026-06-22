package com.example.testkmpapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.testkmpapp.domain.models.NewsItem
import com.example.testkmpapp.navigation.Screens
import com.example.testkmpapp.navigation.ViewModelFactory
import com.example.testkmpapp.presentation.news.NewsDetailScreen
import com.example.testkmpapp.presentation.news.NewsListScreen
import com.example.testkmpapp.utils.Coder
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.*

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        MaterialTheme {
            NavHost(
                navigator = navigator,
                initialRoute = Screens.NewsList.route,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                scene(Screens.NewsList.route) {
                    val viewModel = ViewModelFactory.resolve(Screens.NewsList)
                    NewsListScreen(
                        viewModel = viewModel,
                        onItemClick = { item ->
                            val json = Coder.encode(NewsItem.serializer(), item)
                            navigator.navigate("/details/$json")
                        }
                    )
                }
                scene(Screens.NewsDetails.route) { backStackEntry ->
                    val jsonStr = backStackEntry.path<String>("item")
                    if (jsonStr != null) {
                        val item = Coder.decode(NewsItem.serializer(), jsonStr)
                        NewsDetailScreen(
                            item = item,
                            onBack = { navigator.goBack() }
                        )
                    }
                }
            }
        }
    }
}
