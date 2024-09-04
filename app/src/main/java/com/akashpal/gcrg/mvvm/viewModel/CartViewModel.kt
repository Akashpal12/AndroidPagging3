package com.akashpal.gcrg.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashpal.gcrg.mvvm.models.CartResponse
import com.akashpal.gcrg.mvvm.models.Request
import com.akashpal.gcrg.mvvm.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository):ViewModel() {
    val cartResponse: LiveData<CartResponse> = repository.cartResponse
    fun fetchCartDetails(request: Request) {
        viewModelScope.launch {
            repository.getCartResponse(request)
        }
    }
}