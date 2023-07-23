package com.tabi.nytimes.domain.model

data class Article(
    val url: String?,
    val section: String?,
    val byline: String?,
    val title: String?,
    val abstract: String?,
    val published_date: String?,
    val source: String?,
    val media: ArrayList<Media>,
    var imgUrl: String? = null
)