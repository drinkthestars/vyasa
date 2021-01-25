package com.goofy.goober.api.client

import com.goofy.goober.api.model.ApiCategory
import com.goofy.goober.api.model.ApiPost
import com.goofy.goober.api.service.Api

class ApiClient(
    private val api: Api
) {

    suspend fun getPosts(page: Int): List<ApiPost> {
        return api.getPosts(page)
    }

    suspend fun getCategories(page: Int): List<ApiCategory> {
        return api.getCategories(page)
    }
}
