package com.example.testkmpapp.api.network

import com.example.testkmpapp.domain.models.NewsItemsList

class NewsService(private val httpClient: NetworkClient) {

    suspend fun loadNews(): Result<NewsItemsList> {
        return httpClient.request(PATH)
    }

    companion object {
        private const val PATH = "everything?q=apple"
    }
}
