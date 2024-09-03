package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akashpal.gcrg.mvvm.models.PostModelItem
import com.akashpal.gcrg.mvvm.repository.PostPagingRepository


class PostPagingViewModel(private val repository: PostPagingRepository) : ViewModel() {
    val posts: LiveData<PagingData<PostModelItem>> = repository.getPagePosts().cachedIn(viewModelScope)
}
