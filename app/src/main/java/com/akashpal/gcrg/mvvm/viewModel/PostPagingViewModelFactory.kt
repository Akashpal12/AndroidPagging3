package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akashpal.gcrg.mvvm.repository.PostPagingRepository
import com.akashpal.gcrg.mvvm.repository.PostRepository

class PostPagingViewModelFactory (private val repository: PostPagingRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostPagingViewModel(repository) as T
    }
}