package com.tabi.nytimes.data.model

import com.google.gson.annotations.SerializedName

data class MediaDTO(
    val type: String?,
    val subtype: String?,
    val caption: String?,
    val copyright: String?,
    val approved_for_syndication: Int?,
    @SerializedName("media-metadata")
    val media_metadata:ArrayList<MediaMetadataDTO>
)