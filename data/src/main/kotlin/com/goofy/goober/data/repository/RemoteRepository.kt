package com.goofy.goober.data.repository

import com.goofy.goober.api.client.ApiClient
import com.goofy.goober.api.model.ApiCategory
import com.goofy.goober.api.model.ApiLinks
import com.goofy.goober.api.model.ApiPost
import com.goofy.goober.domain.repository.Repository
import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.model.Excerpt
import com.goofy.goober.domain.model.Link
import com.goofy.goober.domain.model.Links
import com.goofy.goober.domain.model.Post
import com.goofy.goober.domain.model.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepository(
    private val apiClient: ApiClient
) : Repository {

    override suspend fun getPosts(page: Int): List<Post> = withContext(Dispatchers.IO) {
        apiClient
            .getPosts(page)
            .map {  it.toPost() }
    }

    override suspend fun getCategories(page: Int): List<Category> = withContext(Dispatchers.IO) {
        apiClient
            .getCategories(page)
            .map {  it.toCategory() }
    }

    private fun ApiCategory.toCategory(): Category {
        return Category(
            id = id,
            links = links.toLinks(),
            name = name
        )
    }

    private fun ApiPost.toPost(): Post {
        return Post(
            id = id,
            link = link,
            title = Title(rendered = title.rendered),
            excerpt = Excerpt(rendered = excerpt.rendered)
        )
    }

    private fun ApiLinks.toLinks(): Links {
        return Links(
            posts = posts.map { Link(it.url) },
            parent = parent?.map { Link(it.url) } ?: emptyList()
        )
    }
}
