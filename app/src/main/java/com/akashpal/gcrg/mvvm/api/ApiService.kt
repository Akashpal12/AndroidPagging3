package com.akashpal.gcrg.mvvm.api

import com.akashpal.gcrg.mvvm.models.PostModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): Response<List<PostModelItem>>

    @GET("posts")
    suspend fun getPagePosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<PostModelItem>


}