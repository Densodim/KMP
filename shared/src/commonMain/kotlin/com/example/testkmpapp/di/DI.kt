package com.example.testkmpapp.di

import com.example.testkmpapp.api.network.NetworkClient
import com.example.testkmpapp.api.network.NetworkConfiguration
import com.example.testkmpapp.api.network.NewsService

object DI {
    fun getNewsService(): NewsService {
        return NewsService(
            httpClient = NetworkClient(NetworkConfiguration())
        )
    }
}