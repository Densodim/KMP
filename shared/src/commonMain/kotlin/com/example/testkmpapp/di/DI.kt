package com.example.testkmpapp.di

import com.example.testkmpapp.api.network.NetworkClient
import com.example.testkmpapp.api.network.NetworkConfiguration
import com.example.testkmpapp.api.network.NewsService

import com.example.testkmpapp.api.network.NewsUseCase

object DI {
    fun getNewsService(): NewsService {
        return NewsService(NetworkClient(NetworkConfiguration()))
    }

    fun getNewsUseCase(): NewsUseCase {
        return NewsUseCase(getNewsService())
    }
}