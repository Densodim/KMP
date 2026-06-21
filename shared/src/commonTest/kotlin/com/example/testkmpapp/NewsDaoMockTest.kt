package com.example.testkmpapp

import com.example.testkmpapp.domain.models.NewsItemEntity
import com.example.testkmpapp.domain.models.room.NewsListDao
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class NewsDaoMockTest : StringSpec({

    "getAll возвращает замоканные элементы" {
        // Mokkery: мок интерфейса NewsListDao
        val dao = mock<NewsListDao> {
            everySuspend { getAll() } returns listOf(
                NewsItemEntity(
                    author = "author",
                    title = "title",
                    description = "desc",
                    url = "https://example.com/1",
                    urlToImage = null,
                    publishedAt = null,
                    content = null,
                )
            )
        }

        val result = dao.getAll()

        // Kotest: матчеры
        result shouldHaveSize 1
        result.first().title shouldBe "title"

        // Mokkery: проверка вызова
        verifySuspend(exactly(1)) { dao.getAll() }
    }
})
