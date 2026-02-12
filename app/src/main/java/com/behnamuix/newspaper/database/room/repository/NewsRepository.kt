package com.behnamuix.newspaper.room.repository

import com.behnamuix.newspaper.api.loadBBcNews
import com.behnamuix.newspaper.api.loadNews
import com.behnamuix.newspaper.api.model.toEntity
import com.behnamuix.newspaper.room.NewsDAO
import com.behnamuix.newspaper.room.NewsEntity

class NewsRepository(
    private val dao: NewsDAO
) {

    fun getNewsFromDb() = dao.getAllNews()

    suspend fun refreshNews() {
        val apiNews = loadNews()               // API
        val entities = apiNews.map { it.toEntity() }  // تبدیل به Entity
        dao.clear()
        dao.insertAll(entities)                // ذخیره تو Room
    }
}