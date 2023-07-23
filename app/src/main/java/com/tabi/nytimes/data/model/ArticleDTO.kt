package com.tabi.nytimes.data.model

data class ArticleDTO(
    val url: String?,
    val adx_keywords: String?,
    val section: String?,
    val byline: String?,
    val type: String?,
    val title: String?,
    val published_date: String?,
    val abstract: String?,
    val source: String?,
    val uri: String?,
    val media: ArrayList<MediaDTO>
)
