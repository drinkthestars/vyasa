package com.goofy.goober.api.model

import com.google.gson.annotations.SerializedName

data class ApiCategory(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("_links") val links: ApiLinks
)

data class ApiLinks(
    @SerializedName("wp:post_type") val posts: List<ApiLink>,
    @SerializedName("up") val parent: List<ApiLink>?
)

data class ApiLink(
    @SerializedName("href") val url: String
)
