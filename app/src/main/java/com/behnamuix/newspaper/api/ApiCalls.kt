package com.behnamuix.newspaper.api

import com.behnamuix.newspaper.api.model.Article

suspend fun loadNews(): List<Article> {
    return ApiClient.apiService
        .getTopHeadlines("us", "1a74671e124f4ae0bd5b338c0408be00")
        .articles
}

suspend fun loadBBcNews(): List<Article> {
    return ApiClient.apiService
        .getBbcNews("bbc-news", "1a74671e124f4ae0bd5b338c0408be00")
        .articles
}