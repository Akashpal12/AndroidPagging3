package com.akashpal.gcrg.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.akashpal.gcrg.mvvm.api.ApiService
import com.akashpal.gcrg.mvvm.models.PostModelItem

class PostRepository(private val apiService: ApiService) {

    private val postsLiveData = MutableLiveData<List<PostModelItem>>()
    val posts: LiveData<List<PostModelItem>> get() = postsLiveData

    suspend fun getPosts() {
        val result = apiService.getPosts()
        if (result.isSuccessful && result.body() != null) {
            postsLiveData.postValue(result.body())
        }
    }

}