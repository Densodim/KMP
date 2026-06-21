package com.example.testkmpapp.di

import android.content.Context
import androidx.room.Room
import com.example.testkmpapp.domain.models.room.AppDataBase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<AppDataBase> {
        val context: Context = get()
        val dbFile = context.getDatabasePath("news.db")
        getRoomDatabase(
            Room.databaseBuilder<AppDataBase>(
                context = context.applicationContext,
                name = dbFile.absolutePath
            )
        )
    }
}
