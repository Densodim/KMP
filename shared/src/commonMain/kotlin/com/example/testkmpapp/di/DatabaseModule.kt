package com.example.testkmpapp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.testkmpapp.domain.models.room.AppDataBase
import com.example.testkmpapp.domain.models.room.NewsCachedUseCase
import com.example.testkmpapp.domain.models.room.NewsRepository
import com.example.testkmpapp.domain.models.room.SaveNewsUseCase
import com.example.testkmpapp.util.ioDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

// Платформо-зависимый модуль: создаёт AppDataBase
// (на Android нужен Context, на iOS — путь в файловой системе)
expect fun platformModule(): Module

// Общая сборка БД: драйвер SQLite + контекст корутин для запросов
fun getRoomDatabase(builder: RoomDatabase.Builder<AppDataBase>): AppDataBase =
    builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(ioDispatcher)
        .build()

// Repository + use case'ы — общие для обеих платформ
val dataModule = module {
    single { NewsRepository(get()) }
    factory { NewsCachedUseCase(get()) }
    factory { SaveNewsUseCase(get()) }
}
