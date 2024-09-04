package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akashpal.gcrg.mvvm.repository.CartRepository
import com.akashpal.gcrg.mvvm.repository.PostBTokenRepository

class PostBTokenViewModelFactory(private val repository: PostBTokenRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostBTokenViewModel(repository) as T
    }
}