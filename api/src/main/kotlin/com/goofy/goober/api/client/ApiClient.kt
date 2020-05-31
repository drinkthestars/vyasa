package com.goofy.goober.api.client

import com.goofy.goober.api.model.ApiPost
import com.goofy.goober.api.service.Api

class ApiClient(
    private val api: Api
) {

    suspend fun getPosts(): List<ApiPost> {
        return api.getPosts()
    }
}
