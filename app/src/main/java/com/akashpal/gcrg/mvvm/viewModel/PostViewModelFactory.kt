package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akashpal.gcrg.mvvm.repository.PostRepository

class PostViewModelFactory (private val repository: PostRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}