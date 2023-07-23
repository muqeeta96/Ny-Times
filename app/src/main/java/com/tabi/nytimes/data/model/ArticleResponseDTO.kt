package com.tabi.nytimes.data.model

import com.tabi.nytimes.domain.model.Article
import com.tabi.nytimes.domain.model.ArticleResponse
import com.tabi.nytimes.domain.model.Media
import com.tabi.nytimes.domain.model.MediaMetadata

data class ArticleResponseDTO(
    val results: ArrayList<ArticleDTO>,
    val copyright: String,
    val status: String,
    val num_results: String,
)

fun ArticleResponseDTO.toArticleResponse(): ArticleResponse {
    return ArticleResponse(this.results.map {
        Article(
            it.url,
            it.section,
            it.byline,
            it.title,
            it.abstract,
            it.published_date,
            it.source,
            toMedia(it.media)

        )
    } as ArrayList<Article>, this.status)
}

fun ArticleResponseDTO.toMedia(mediaDTOList: ArrayList<MediaDTO>): ArrayList<Media> {
    return mediaDTOList.map { mediaDTO ->

        Media(
            mediaDTO.type, mediaDTO.subtype, mediaDTO.caption, mediaDTO.media_metadata.map {
                MediaMetadata(
                    it.url, it.height, it.width
                )
            } as ArrayList<MediaMetadata>
        )
    } as ArrayList<Media>
}

fun ArticleResponseDTO.toMediaMetadata(mediaMetadataDTOList: ArrayList<MediaMetadataDTO>): ArrayList<MediaMetadata> {
    return mediaMetadataDTOList.map {
        MediaMetadata(
            it.url, it.height, it.width
        )
    } as ArrayList<MediaMetadata>
}
