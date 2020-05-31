package com.goofy.goober.domain.model

data class Posts(
    val posts : List<Post>
)

data class Post(
    val id: Long,
    val link: String,
    val title: Title,
    val excerpt: Excerpt
)

data class Title(
    val rendered: String
)

data class Excerpt(
    val rendered: String
)
