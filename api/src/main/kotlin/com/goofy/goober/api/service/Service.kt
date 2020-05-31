package com.goofy.goober.api.service

import com.goofy.goober.api.model.ApiPost
import retrofit2.http.GET

interface Api {
    @GET("posts?_fields=author,id,excerpt,title,link")
    suspend fun getPosts(): List<ApiPost>
}
