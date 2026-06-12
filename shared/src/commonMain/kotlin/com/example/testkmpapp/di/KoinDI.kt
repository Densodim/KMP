package com.example.testkmpapp.di

import com.example.testkmpapp.api.network.NewsService
import com.example.testkmpapp.api.network.NewsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Класс для доступа к зависимостям со стороны iOS (Swift).
 */
class KoinDI: KoinComponent {
    val newsService: NewsService by inject()
    val newsUseCase: NewsUseCase by inject()
}
