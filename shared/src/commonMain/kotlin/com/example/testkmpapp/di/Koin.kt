package com.example.testkmpapp.di

import com.example.testkmpapp.api.network.NetworkClient
import com.example.testkmpapp.api.network.NewsService
import com.example.testkmpapp.api.network.NewsUseCase
import com.example.testkmpapp.domain.models.NewsViewModels
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val serviceModule = module {
    single { NetworkClient() }
    single { NewsService(get()) }
}

val usecaseModule = module {
    factory { NewsUseCase(get()) }
}

val vmModule = module {
    factory { NewsViewModels() }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(serviceModule, usecaseModule, vmModule)
    }
}


// Для вызова из iOS
fun initKoin() = initKoin {}

object KoinDIFactory {
    val di: KoinDI by lazy { KoinDI() }
}
