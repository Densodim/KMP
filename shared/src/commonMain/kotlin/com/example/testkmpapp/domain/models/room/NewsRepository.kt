package com.example.testkmpapp.domain.models.room

import com.example.testkmpapp.domain.models.NewsItem
import com.example.testkmpapp.domain.models.NewsItemEntity
import com.example.testkmpapp.domain.models.toEntity
import com.example.testkmpapp.domain.models.toItem
import com.example.testkmpapp.presentation.news.BaseUseCase


class NewsRepository constructor(
    private val database: AppDataBase
){
    private val dao: NewsListDao by lazy {
        database.getDao()
    }

    suspend fun getAll(): List<NewsItemEntity> {
        return dao.getAll()
    }
    suspend fun update(item: NewsItemEntity){
        dao.updateItem(item)
    }

    suspend fun save(item: NewsItemEntity){
        val all = getAll()

        if(all.find { it.url == item.url } == null){
            dao.insert(item)
        }else{
            update(item)
        }
    }

    suspend fun saveAll(items:List<NewsItemEntity>){
        items.forEach { save(it) }
    }
}

class NewsCachedUseCase(private val repository: NewsRepository): BaseUseCase<Unit, List<NewsItem>>(){
    override suspend fun execute(param: Unit): List<NewsItem> {
        return repository.getAll().map { it.toItem() }
    }
}

class SaveNewsUseCase(private  val repository: NewsRepository): BaseUseCase<NewsItem?, Unit>(){
    override suspend fun execute(param: NewsItem?): Unit {
        if (param != null) {
            repository.save(param.toEntity())
        }
    }
}