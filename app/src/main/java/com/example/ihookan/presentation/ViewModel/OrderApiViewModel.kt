package com.example.ihookan.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ihookan.domain.useCase.OrderApiUseCase
import kotlinx.coroutines.launch
import java.util.*

class OrderApiViewModel(private val orderApiUseCase: OrderApiUseCase): ViewModel() {

    fun insert(name:String, phone:String, description:String, orderPrice:String, orderDate: String) = viewModelScope.launch {
        orderApiUseCase.insert(name, phone, description, orderPrice, orderDate)
    }
}