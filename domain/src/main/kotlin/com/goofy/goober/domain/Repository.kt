package com.goofy.goober.domain

import com.goofy.goober.domain.model.Posts

interface Repository {

    suspend fun getPosts(): Posts
}
