package com.akashpal.gcrg.mvvm.api

import com.akashpal.gcrg.mvvm.models.CartResponse
import com.akashpal.gcrg.mvvm.models.PostModelItem
import com.akashpal.gcrg.mvvm.models.Request
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): Response<List<PostModelItem>>

    @GET("posts")
    suspend fun getPagePosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<PostModelItem>

    @POST("api/APIKisokProductDetail.asmx/CartDetails")
    suspend fun getPostApi(
        @Body post: Request
    ): Response<CartResponse>

    @POST("/posts")
    suspend fun createPostBearer(
        @Body post: Request,
        @Header("Authorization") authHeader: String
    ): Response<CartResponse>


}