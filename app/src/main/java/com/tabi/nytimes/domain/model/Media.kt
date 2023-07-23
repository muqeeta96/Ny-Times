package com.tabi.nytimes.domain.model

import com.google.gson.annotations.SerializedName

data class Media(
    val type: String?,
    val subtype: String?,
    val caption: String?,
    @SerializedName("media-metadata")
    val media_metadata: ArrayList<MediaMetadata>
)