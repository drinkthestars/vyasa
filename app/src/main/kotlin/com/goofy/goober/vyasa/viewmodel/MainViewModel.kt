package com.goofy.goober.vyasa.viewmodel

import androidx.lifecycle.ViewModel
import com.goofy.goober.domain.flow.Action
import com.goofy.goober.domain.flow.State
import com.goofy.goober.vyasa.controller.Interactor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val interactor: Interactor
) : ViewModel() {

    val state: StateFlow<State> get() = reducer.state

    private val reducer = Reducer(
        initialState = State.CategoriesLoading,
        onReduce = { _, _, newState -> onNewState(newState) }
    )

    init {
        launch {
            val categoryPages = MutableStateFlow(interactor.categoryPage())
            reducer.reduce(Action.ShowCategories(categoryPages))
        }
    }

    fun consume(uiAction: Action.UiAction) {
        reducer.reduce(uiAction)
    }

    private fun onNewState(state: State) {
        when (state) {
            is State.Categories -> {
                launch {
                    state.categoryPages.value = interactor.categoryPage(state)
                }
            }
            is State.CategoryPostsLoading -> {
                launch {
                    val postList = interactor.postList(page = 1)
                    reducer.reduce(Action.ShowCategoryPosts(postList))
                }
            }
            else -> {
            }
        }
    }
}
