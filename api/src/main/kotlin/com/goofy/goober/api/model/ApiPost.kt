package com.goofy.goober.api.model

import com.google.gson.annotations.SerializedName

data class ApiPost(
    @SerializedName("id") val id: Long,
    @SerializedName("link") val link: String,
    @SerializedName("title") val title: ApiTitle,
    @SerializedName("excerpt") val excerpt: ApiExcerpt
)

data class ApiTitle(
    @SerializedName("rendered") val rendered: String
)

data class ApiExcerpt(
    @SerializedName("rendered") val rendered: String
)
