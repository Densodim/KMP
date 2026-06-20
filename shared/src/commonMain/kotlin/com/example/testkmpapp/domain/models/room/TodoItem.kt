package com.example.testkmpapp.domain.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TodoItem(
    val id: Long = 0,
    val title: String,
    val content: String,
    val data: String
)

@Entity
data class TodoEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val data: String
)

fun TodoEntity.toItem(): TodoItem = TodoItem(id, title, content, data)

fun TodoItem.toEntity(): TodoEntity = TodoEntity(title = title, content = content, data= data)