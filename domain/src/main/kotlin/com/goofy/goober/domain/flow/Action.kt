package com.goofy.goober.domain.flow

import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.model.Link
import com.goofy.goober.domain.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class Action {

    sealed class UiAction : Action() {
        data class LoadCategoryPosts(val link: Link) : UiAction()
        object LoadMoreCategories : UiAction()
        object GoBack : UiAction()
    }

    data class ShowCategories(val categoryPages: MutableStateFlow<Page<Category>>) : Action()
    data class ShowCategoryPosts(val posts: List<Post>) : Action()
}


data class Page<T>(
    val data: List<T>,
    val hasNextPage: Boolean
)
