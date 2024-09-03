package com.akashpal.gcrg.mvvm.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.akashpal.gcrg.mvvm.api.ApiService
import com.akashpal.gcrg.mvvm.models.PostModelItem
import com.akashpal.gcrg.mvvm.paging.PostPagingSource

class PostPagingRepository (private val apiService: ApiService) {
    fun getPagePosts()= Pager<Int, PostModelItem> (
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { PostPagingSource(apiService) }
    ).liveData
}