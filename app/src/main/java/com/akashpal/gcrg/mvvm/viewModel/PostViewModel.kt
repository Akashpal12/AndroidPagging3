package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.akashpal.gcrg.mvvm.models.PostModelItem
import com.akashpal.gcrg.mvvm.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel (private val repository: PostRepository) : ViewModel(){
    val posts: LiveData<List<PostModelItem>> get() = repository.posts

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPosts()
        }
    }

    // Function to get a specific post by ID
    fun getPostById(id: Int): LiveData<PostModelItem?> {
        return posts.map { list ->
            list.find { it.id == id }
        }
    }

    // Function to get a list of post titles for the Spinner
    fun getPostTitles(): LiveData<List<String>> {
        return posts.map { list ->
            list.map { "${it.userId} - ${it.id}" }
        }
    }

}