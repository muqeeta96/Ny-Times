package com.tabi.nytimes.data.remote

import com.tabi.nytimes.BuildConfig.API_KEY
import com.tabi.nytimes.data.model.ArticleResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesRequest {
    @GET("{period}.json")
    suspend fun getArticles(
        @Path("period") period: Int = 1, @Query("api-key") apiKey: String = API_KEY
    ): Response<ArticleResponseDTO>

    @GET("{period}.json")
    suspend fun getArticle(
        @Path("period") period: Int = 1, @Query("api-key") apiKey: String = API_KEY
    ): Response<ArticleResponseDTO>

}