package com.tabi.nytimes.domain.repository

import com.tabi.nytimes.data.model.ArticleResponseDTO
import retrofit2.Response

interface INetworkRepository {
    suspend fun getArticles(period: Int): Response<ArticleResponseDTO>?
//    suspend fun getMovieDetail(moveId: Int): Response<MovieDetailDTO>
//
//    suspend fun getMovies(page: Int): Response<MovieResponseDTO>?
//
//    suspend fun getSearchMovieResult(query: String, page: Int): Response<MovieResponseDTO>
}