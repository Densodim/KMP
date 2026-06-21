package com.example.testkmpapp.domain.models.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testkmpapp.domain.models.NewsItemEntity

@Database(entities = [NewsItemEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getDao(): NewsListDao
}