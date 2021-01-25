package com.goofy.goober.api.service

import com.goofy.goober.api.model.ApiCategory
import com.goofy.goober.api.model.ApiPost
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("posts?_fields=author,id,excerpt,title,link")
    suspend fun getPosts(@Query("page") page: Int): List<ApiPost>

    @GET("categories")
    suspend fun getCategories(@Query("page") page: Int): List<ApiCategory>
}
