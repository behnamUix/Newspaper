package com.behnamuix.newspaper.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.behnamuix.newspaper.api.model.Article
import com.behnamuix.newspaper.api.model.Source
import okio.`-DeprecatedOkio`.source

@Entity(tableName = "news")
data class NewsEntity(
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    @PrimaryKey
    val url: String,   // کلید اصلی
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

fun NewsEntity.toArticle(): Article {
    return Article(
        url = url,
        title = title,
        description = description,
        publishedAt = publishedAt,
        source = Source(id = null, name = sourceName),
        author = author,
        urlToImage = urlToImage,
        content = content
    )
}
