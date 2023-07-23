package com.tabi.nytimes.di

import com.tabi.nytimes.data.remote.ArticlesRequest
import com.tabi.nytimes.BuildConfig.BASE_URL
import com.tabi.nytimes.data.repository.NetworkRepository
import com.tabi.nytimes.domain.repository.INetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getNetworkRepo(articlesRequest: ArticlesRequest): INetworkRepository {
        return NetworkRepository(articlesRequest)
    }

    @Singleton
    @Provides
    fun getApiInstance(): ArticlesRequest {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ArticlesRequest::class.java)
    }
}