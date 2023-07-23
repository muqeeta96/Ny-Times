package com.tabi.nytimes.domain.dataService

import com.google.common.truth.Truth.assertThat
import com.tabi.nytimes.data.repository.FakeNetworkRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ArticleServiceTest {
    private lateinit var fakeNetworkRepository: FakeNetworkRepository

    @Before
    fun setUp() {
        fakeNetworkRepository = FakeNetworkRepository()
    }

    @Test
    fun testGetArticlesResponseIsSuccessful() = runTest {
        val response = fakeNetworkRepository.getArticles(1)
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun testGetArticlesResponseIsFailed() = runTest {
        fakeNetworkRepository.setResponseCode(403)
        val response = fakeNetworkRepository.getArticles(1)
        assertThat(response.isSuccessful).isFalse()
    }


}