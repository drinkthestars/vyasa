package com.goofy.goober.domain

import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.repository.Repository

class Categories(
    private val repo: Repository
) {
    suspend operator fun invoke(page: Int): List<Category> {
        return repo.getCategories(page)
    }
}
