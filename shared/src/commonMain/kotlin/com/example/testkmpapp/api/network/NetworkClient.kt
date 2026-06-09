package com.example.testkmpapp.api.network

import com.example.testkmpapp.domain.models.NewsItemsList
import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient

class NetworkClient(
    private val networkConfiguration: NetworkConfiguration
) {
    suspend fun request(url: String): Result<NewsItemsList> {
        // TODO: API
        return Result.failure(NotImplementedError("Network request is not implemented yet for $url"))
    }
}
