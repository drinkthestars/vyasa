package com.goofy.goober.domain.model

data class Category(
    val id: Long,
    val name: String,
    val links: Links
)

data class Links(
    val posts: List<Link>,
    val parent: List<Link>
)

data class Link(
    val url: String
)
