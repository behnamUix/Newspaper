package com.behnamuix.newspaper.api

import com.behnamuix.newspaper.api.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): NewsResponse
    @GET("v2/top-headlines")
    suspend fun getBbcNews(
        @Query("sources") source: String = "bbc-news",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}