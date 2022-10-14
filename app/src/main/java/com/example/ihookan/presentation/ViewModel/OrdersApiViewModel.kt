package com.example.ihookan.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ihookan.domain.useCase.OrdersApiUseCase
import kotlinx.coroutines.launch

class OrdersApiViewModel(private val orderApiUseCase: OrdersApiUseCase): ViewModel() {



    fun insert(email:String, description:String, orderPrice:String, orderDate: String) = viewModelScope.launch {
        orderApiUseCase.insert(email, description, orderPrice, orderDate)
    }
}