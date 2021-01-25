package com.goofy.goober.vyasa.controller

import com.goofy.goober.domain.Categories
import com.goofy.goober.domain.Posts
import com.goofy.goober.domain.flow.Page
import com.goofy.goober.domain.flow.State
import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.model.Post
import kotlinx.coroutines.flow.MutableStateFlow

class Interactor(
    private val categories: Categories,
    private val posts: Posts
) {

    suspend fun postList(categoryId: Int = 0, page: Int): List<Post> {
        return posts(page)
    }

    suspend fun categoryPage(
        state: State.Categories = defaultCategoriesState()
    ): Page<Category> {
        val newCategories = categories(state.categoryPageNumber)
        return Page(
            data = state.categoryPages.value.data + newCategories,
            hasNextPage = newCategories.isNotEmpty()
        )
    }

    private fun defaultCategoriesState(): State.Categories {
        return State.Categories(
            categoryPageNumber = 1,
            categoryPages = MutableStateFlow(Page(emptyList(), true))
        )
    }
}
