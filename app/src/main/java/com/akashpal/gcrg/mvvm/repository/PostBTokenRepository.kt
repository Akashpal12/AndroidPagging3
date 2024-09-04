package com.akashpal.gcrg.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akashpal.gcrg.mvvm.api.ApiService
import com.akashpal.gcrg.mvvm.models.CartResponse
import com.akashpal.gcrg.mvvm.models.Request

class PostBTokenRepository (private val apiService: ApiService) {
    private val _cartResponse = MutableLiveData<CartResponse>()
    val cartResponse: LiveData<CartResponse> = _cartResponse
    suspend fun getCartResponse(request: Request,token: String) {
        try {
            val response = apiService.createPostBearer(request,"Bearer $token")
            if (response.isSuccessful) {
                _cartResponse.postValue(response.body())
            }
        } catch (e: Exception) {

        }
    }
}