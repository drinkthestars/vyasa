package com.goofy.goober.domain.flow

import com.goofy.goober.domain.flow.Action.ShowCategories
import com.goofy.goober.domain.flow.Action.ShowCategoryPosts
import com.goofy.goober.domain.flow.Action.UiAction.GoBack
import com.goofy.goober.domain.flow.Action.UiAction.LoadCategoryPosts
import com.goofy.goober.domain.flow.Action.UiAction.LoadMoreCategories
import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.model.Post
import kotlinx.coroutines.flow.MutableStateFlow

sealed class State {

    abstract fun reduce(action: Action): State

    object CategoriesLoading : State() {
        override fun reduce(action: Action): State {
            return when (action) {
                is GoBack -> Exit
                is ShowCategories -> Categories(categoryPages = action.categoryPages)

                is LoadCategoryPosts,
                LoadMoreCategories,
                is ShowCategoryPosts -> this
            }
        }
    }

    data class Categories(
        val categoryPageNumber: Int = 1,
        val categoryPages: MutableStateFlow<Page<Category>>
    ) : State() {
        override fun reduce(action: Action): State {
            return when (action) {
                is GoBack -> Exit
                is LoadMoreCategories -> Categories(categoryPageNumber + 1, categoryPages)
                is LoadCategoryPosts -> CategoryPostsLoading(categoryPageNumber, categoryPages)

                is ShowCategories,
                is ShowCategoryPosts -> this
            }
        }
    }

    data class CategoryPostsLoading(
        val categoryPageNumber: Int,
        val categoriesPage: MutableStateFlow<Page<Category>>
    ) : State() {
        override fun reduce(action: Action): State {
            return when (action) {
                is GoBack -> CategoriesLoading
                is ShowCategoryPosts -> CategoryPosts(
                    categoryPageNumber = categoryPageNumber,
                    categoriesPage = categoriesPage,
                    posts = action.posts
                )

                LoadMoreCategories,
                is ShowCategories,
                is LoadCategoryPosts -> this
            }
        }
    }

    data class CategoryPosts(
        val categoryPageNumber: Int,
        val categoriesPage: MutableStateFlow<Page<Category>>,
        val posts: List<Post>
    ) : State() {
        override fun reduce(action: Action): State {
            return when (action) {
                is GoBack -> Categories(categoryPageNumber, categoriesPage)

                LoadMoreCategories,
                is ShowCategories,
                is LoadCategoryPosts,
                is ShowCategoryPosts -> this
            }
        }
    }

    object Exit : State() {
        override fun reduce(action: Action): State = this
    }
}
