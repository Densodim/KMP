package com.example.testkmpapp.domain.models.room

import androidx.room.RoomDatabaseConstructor

// actual-реализации для каждой платформы генерирует сам Room (KSP),
// поэтому писать их вручную не нужно — отсюда @Suppress
@Suppress("NO_ACTUAL_FOR_EXPECT", "KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}