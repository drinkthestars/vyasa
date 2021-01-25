package com.goofy.goober.domain.repository

import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.model.Post

interface Repository {

    suspend fun getPosts(page: Int): List<Post>

    suspend fun getCategories(page: Int): List<Category>
}
