package com.akashpal.gcrg.mvvm.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akashpal.gcrg.mvvm.api.ApiService
import com.akashpal.gcrg.mvvm.models.PostModelItem

class PostPagingSource(private val apiService: ApiService) : PagingSource<Int, PostModelItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModelItem> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPagePosts(page = currentPage, limit = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostModelItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}