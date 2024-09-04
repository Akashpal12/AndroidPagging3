package com.akashpal.gcrg.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akashpal.gcrg.mvvm.api.ApiService
import com.akashpal.gcrg.mvvm.models.CartResponse
import com.akashpal.gcrg.mvvm.models.PostModelItem
import com.akashpal.gcrg.mvvm.models.Request

class CartRepository (private val apiService: ApiService) {

    private val _cartResponse = MutableLiveData<CartResponse>()
    val cartResponse: LiveData<CartResponse> = _cartResponse
    suspend fun getCartResponse(request: Request) {
        try {
            val response = apiService.getPostApi(request)
            if (response.isSuccessful) {
                _cartResponse.postValue(response.body())
            }
        } catch (e: Exception) {

        }
    }

}