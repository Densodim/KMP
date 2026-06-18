package com.example.testkmpapp.di

import com.example.testkmpapp.api.network.NewsService
import com.example.testkmpapp.api.network.NewsUseCase
import com.example.testkmpapp.domain.models.NewsViewModels
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDI: KoinComponent {
    val newsService: NewsService by inject()
    val newsUseCase: NewsUseCase by inject()
    val newsViewModels: NewsViewModels by inject()
}
