package com.tabi.nytimes.data.repository

import com.tabi.nytimes.data.model.ArticleResponseDTO
import com.tabi.nytimes.domain.repository.INetworkRepository
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response


class FakeNetworkRepository : INetworkRepository {
    private var responseCode = 200
    override suspend fun getArticles(period: Int): Response<ArticleResponseDTO> {
        return if (responseCode == 200) {
            Response.success(responseCode, ArticleResponseDTO(arrayListOf(), "", "", ""))
        } else {
            Response.error(
                responseCode, ResponseBody.create(
                    MediaType.parse("application/json"),
                    "{\"key\":[\"error\"]}"
                )
            )
        }
    }

    fun setResponseCode(code: Int) {
        responseCode = code
    }

}