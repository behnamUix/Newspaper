package com.behnamuix.newspaper.api.model

import com.behnamuix.newspaper.room.NewsEntity

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String
)

//هر Article تبدیل به NewsEntity شده.
fun Article.toEntity(): NewsEntity {
    return NewsEntity(
        url = this.url,
        title = this.title,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceName = source.name,   // فقط اسم سورس
        author = this.author,
        urlToImage = this.urlToImage,
        content = this.content
    )
}