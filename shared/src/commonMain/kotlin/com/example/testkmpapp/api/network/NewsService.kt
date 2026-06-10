package com.example.testkmpapp.api.network

import com.example.testkmpapp.domain.models.NewsItemsList

class NewsService() {
    private val httpClient: NetworkClient = NetworkClient(
        networkConfiguration = TODO(),
        networkConfig = TODO(),
        httpClient = TODO()
    )
    suspend fun loadNews(): Result<NewsItemsList> {
        //TODO: change DI
        return httpClient.request(URL)
    }

    companion object {
        private const val URL = "https://newsapi.org/v2/everything?q=apple"
    }
}
