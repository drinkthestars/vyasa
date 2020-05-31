package com.goofy.goober.data

import com.goofy.goober.api.client.ApiClient
import com.goofy.goober.domain.Repository
import com.goofy.goober.domain.model.Excerpt
import com.goofy.goober.domain.model.Post
import com.goofy.goober.domain.model.Posts
import com.goofy.goober.domain.model.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(
    private val apiClient: ApiClient
) : Repository {

    override suspend fun getPosts(): Posts = withContext(Dispatchers.IO) {
        apiClient
            .getPosts()
            .run {
                Posts(
                    this.map {
                        Post(
                            id = it.id,
                            link = it.link,
                            title = Title(rendered = it.title.rendered),
                            excerpt = Excerpt(rendered = it.excerpt.rendered)
                        )
                    }
                )
            }
    }
}
