package com.goofy.goober.domain.usecase

import com.goofy.goober.domain.Repository
import com.goofy.goober.domain.model.Posts

class GetPosts(
    private val repo: Repository
) {
    suspend operator fun invoke(): Posts {
        return repo.getPosts()
    }
}
