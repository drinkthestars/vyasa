package com.goofy.goober.vyasa

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.goofy.goober.domain.flow.Action.UiAction
import com.goofy.goober.domain.flow.Action.UiAction.LoadCategoryPosts
import com.goofy.goober.domain.flow.Page
import com.goofy.goober.domain.flow.State
import com.goofy.goober.ui.CategoryRow
import com.goofy.goober.ui.PostRow

@Composable
internal fun App(
    state: State,
    uiEventPerformer: UiEventPerformer
) {
    when (state) {
        is State.CategoriesLoading -> LoadingSpinner()
        is State.Categories -> CategoriesList(state, uiEventPerformer)
        is State.CategoryPostsLoading -> LoadingSpinner()
        is State.CategoryPosts -> CategoryPostsList(state)
        is State.Exit -> uiEventPerformer.exit()
    }
}

@Composable
private fun LoadingSpinner() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.preferredHeight(52.dp).preferredWidth(52.dp),
            color = Color.Magenta,
            strokeWidth = 3.dp
        )
    }
}

@Composable
private fun CategoryPostsList(state: State.CategoryPosts) {
    LazyColumnFor(items = state.posts) {
        PostRow(post = it)
    }
}

@Composable
private fun CategoriesList(
    state: State.Categories,
    uiEventPerformer: UiEventPerformer
) {
    AutoLoadAdapterList(
        page = state.categoryPages.collectAsState().value,
        progressBarCallback = {
            CircularProgressIndicator(
                color = Color.Magenta
            )
        },
        itemCallback = {
            CategoryRow(
                category = it,
                onClick = { uiEventPerformer.performAction(LoadCategoryPosts(it.links.posts.first())) }
            )
        },
        loadNextPage = { uiEventPerformer.performAction(UiAction.LoadMoreCategories) }
    )
}

@Composable
fun <T> AutoLoadAdapterList(
    page: Page<T>,
    progressBarCallback: @Composable() () -> Unit,
    itemCallback: @Composable() (T) -> Unit,
    loadNextPage: () -> Unit
) {
    val loadingState = remember { LoadingState() }

    if (!page.hasNextPage) {
        loadingState.noMoreItems = true
    } else if (loadingState.isLoading) {
        loadingState.isLoading = false
    }

    LazyColumnFor(page.data) {
        if (!loadingState.noMoreItems && shouldLoadMore(page.data, it)) {
            loadingState.isLoading = true
            loadNextPage()
        }

        if (atLastItem(page.data, it) && loadingState.isLoading) {
            Column {
                itemCallback(it)
                Spacer(modifier = Modifier.preferredHeight(16.dp))
                progressBarCallback()
                Spacer(modifier = Modifier.preferredHeight(16.dp))
            }
        } else {
            itemCallback(it)
        }
    }
}

data class LoadingState(
    var isLoading: Boolean = false,
    var noMoreItems: Boolean = false
)

private fun <T> atLastItem(data: List<T>, it: T) = data.indexOf(it) == data.lastIndex
private fun <T> shouldLoadMore(data: List<T>, it: T) = (data.size - data.indexOf(it)) <= 2
