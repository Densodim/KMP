package com.example.testkmpapp.api.network

import com.example.testkmpapp.domain.models.NewsItemsList
import com.example.testkmpapp.util.ioDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in T, out R>(
    private val dispatcher: CoroutineDispatcher = ioDispatcher
) {
    abstract suspend fun execute(params: T): R

    suspend operator fun invoke(params: T): Result<R> = withContext(dispatcher) {
        runCatching { execute(params) }
    }
}

class NewsUseCase(): BaseUseCase<Unit, NewsItemsList?>() {
    //TODO change DI

    private val newsServise: NewsService = NewsService()

    override suspend fun execute(params: Unit): NewsItemsList? {
        return newsServise.loadNews()
    }
}