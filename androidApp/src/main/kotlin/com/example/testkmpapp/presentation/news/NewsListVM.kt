package com.example.testkmpapp.presentation.news

import com.example.testkmpapp.domain.models.NewsItem

class NewsListVM(items: List<NewsItem>) : View {
    override val presenter: Presenter? by lazy {
        NewsPresenter().apply {
            attach(this@NewsListVM)
        }
    }

    fun loadNews() {}
}