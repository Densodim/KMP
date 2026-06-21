package com.example.testkmpapp

import com.example.testkmpapp.api.network.NetworkClient
import com.example.testkmpapp.api.network.NetworkConfiguration
import com.example.testkmpapp.api.network.NewsService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// Собирает NewsService поверх NetworkClient с подменённым Ktor-движком (MockEngine)
private fun serviceWith(
    status: HttpStatusCode,
    body: String,
): NewsService {
    val engine = MockEngine {
        respond(
            content = body,
            status = status,
            headers = headersOf(HttpHeaders.ContentType, "application/json"),
        )
    }
    val httpClient = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    return NewsService(NetworkClient(httpClient = httpClient))
}

class NewsServiceTest : StringSpec({

    "loadNews парсит успешный ответ в NewsItemsList" {
        val json = """
            {
              "totalResults": 2,
              "articles": [
                {
                  "author": "John",
                  "title": "Apple news",
                  "description": "desc",
                  "url": "https://example.com/1",
                  "publishedAt": "2026-06-21",
                  "content": "content",
                  "urlToImage": "https://example.com/1.png"
                },
                {
                  "author": null,
                  "title": "Second",
                  "description": null,
                  "url": "https://example.com/2",
                  "publishedAt": null,
                  "content": null,
                  "urlToImage": null
                }
              ]
            }
        """.trimIndent()

        val result = serviceWith(HttpStatusCode.OK, json).loadNews()

        result.isSuccess shouldBe true
        val list = result.getOrThrow()
        list.totalResults shouldBe 2
        list.articles shouldHaveSize 2
        list.articles.first().title shouldBe "Apple news"
        list.articles.first().isFavorite shouldBe false
    }

    "loadNews возвращает failure при ошибке сервера" {
        val result = serviceWith(HttpStatusCode.InternalServerError, "oops").loadNews()

        result.isFailure shouldBe true
    }

    "loadNews шлёт правильный путь и заголовок X-Api-Key" {
        // перехватываем исходящий запрос
        var captured: HttpRequestData? = null
        val engine = MockEngine { request ->
            captured = request
            respond(
                content = """{"totalResults":0,"articles":[]}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }
        val httpClient = HttpClient(engine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
        val service = NewsService(NetworkClient(httpClient = httpClient))

        service.loadNews()

        val request = captured!!
        request.url.toString() shouldContain "everything?q=apple"
        request.headers["X-Api-Key"] shouldBe NetworkConfiguration.API_KEY
    }
})
