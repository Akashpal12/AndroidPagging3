package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashpal.gcrg.mvvm.models.CartResponse
import com.akashpal.gcrg.mvvm.models.Request
import com.akashpal.gcrg.mvvm.repository.CartRepository
import com.akashpal.gcrg.mvvm.repository.PostBTokenRepository
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class PostBTokenViewModel(private val repository: PostBTokenRepository):ViewModel() {
    val cartResponse: LiveData<CartResponse> = repository.cartResponse
    fun fetchCartDetails(request: Request,token: String) {
        viewModelScope.launch {
            repository.getCartResponse(request,token)
        }
    }
}