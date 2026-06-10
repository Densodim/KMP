package com.example.testkmpapp.api.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

enum class Method {
    GET,
    POST,
    PUT,
    DELETE
}

expect fun createHttpClient(): HttpClient

class NetworkClient(
    private val networkConfiguration: NetworkConfiguration,
    val networkConfig: NetworkConfig = NetworkConfig(),
    val httpClient: HttpClient = createHttpClient()
) {
    suspend inline fun <reified T> request(
        path: String,
        method: Method = Method.GET,
        body: Any? = null,
    ): Result<T> {
        val url = "${NetworkConfiguration.BASE_URL}$path"
        return try {
            val data = when (method) {
                Method.GET -> httpClient.get(url) {
                    networkConfig.header.forEach { (key, value) ->
                        headers.append(key, value)
                    }
                }
                Method.POST -> httpClient.post(url){
                    contentType(ContentType.Application.Json)
                    setBody(body)
                    networkConfig.header.forEach { (key, value) ->
                        headers.append(key, value)
                    }
                }
                Method.PUT -> httpClient.put(url) {
                    contentType(ContentType.Application.Json)
                    setBody(body)
                    networkConfig.header.forEach { (key, value) ->
                        headers.append(key, value)
                    }
                }
                Method.DELETE -> httpClient.delete(url) {
                    networkConfig.header.forEach { (key, value) ->
                        headers.append(key, value)
                    }
                }
            }
            val result = data.body<T>()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
