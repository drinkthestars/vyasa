package com.goofy.goober.domain

import com.goofy.goober.domain.repository.Repository
import com.goofy.goober.domain.model.Post

class Posts(
    private val repo: Repository
) {
    suspend operator fun invoke(page: Int): List<Post> {
        return repo.getPosts(page)
    }
}
