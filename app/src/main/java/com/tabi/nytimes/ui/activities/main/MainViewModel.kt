package com.tabi.nytimes.ui.activities.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabi.nytimes.domain.dataService.ArticleService
import com.tabi.nytimes.domain.model.Article
import com.tabi.nytimes.domain.model.ArticleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val articleService: ArticleService) : ViewModel() {
    var refreshArticles = MutableLiveData(1)
    var clickedArticle: MutableLiveData<Article> = MutableLiveData()

    val articleViewStateData: MutableLiveData<ArticleResponse> = MutableLiveData()
    val errorViewStateData: MutableLiveData<String?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isResultFound: MutableLiveData<Boolean> = MutableLiveData(false)
    var isMenuClicked = false
    val job = Job()

    init {
        getArticles(1)
    }

    fun getArticles(period: Int) {
        if (job.isActive) {
            job.cancel(null)
        }
        viewModelScope.launch(Dispatchers.IO) {

            val resource = articleService.getArticles(period)
            if (resource.data != null) {
                isLoading.postValue(false)
                val movieResponse = resource.data as ArticleResponse
                isResultFound.postValue(movieResponse.results.isEmpty().not())
                articleViewStateData.postValue(movieResponse)
            } else {
                isLoading.postValue(false)
                isResultFound.postValue(false)
                errorViewStateData.postValue(resource.error)
            }

        }
    }
}