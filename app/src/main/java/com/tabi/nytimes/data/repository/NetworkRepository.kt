package com.tabi.nytimes.data.repository

import com.tabi.nytimes.data.model.ArticleResponseDTO
import com.tabi.nytimes.data.remote.ArticlesRequest
import com.tabi.nytimes.domain.repository.INetworkRepository
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val articlesRequest: ArticlesRequest) :
    INetworkRepository {

    override suspend fun getArticles(period: Int): Response<ArticleResponseDTO> {
        return articlesRequest.getArticles(period)
    }
}