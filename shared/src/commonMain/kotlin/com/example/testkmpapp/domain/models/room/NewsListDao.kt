package com.example.testkmpapp.domain.models.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.testkmpapp.domain.models.NewsItemEntity

@Dao
interface NewsListDao {
    @Insert
    suspend fun insert(item: NewsItemEntity)

    @Query("SELECT count(*) FROM news")
    suspend fun count(): Int

    @Query("SELECT * FROM news")
    suspend fun getAll(): List<NewsItemEntity>

    @Update
    suspend fun updateItem(item: NewsItemEntity)
}