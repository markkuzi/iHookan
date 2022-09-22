package com.example.ihookan.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.domain.useCase.OrderUseCase
import kotlinx.coroutines.launch

class OrderViewModel(private val orderUseCase: OrderUseCase): ViewModel() {

    fun startInsert(nameOrder:String, phoneOrder:String, descriptionOrder:String, priceOrder: String, orderDate:String) {
        insert(
            OrderModel(0, nameOrder, phoneOrder, descriptionOrder, priceOrder, orderDate)
        )
    }

    private fun insert(orderModel: OrderModel) = viewModelScope.launch {
        orderUseCase.insert(orderModel)
    }

    val loadOrder = orderUseCase.loadOrder()


    fun clear() = viewModelScope.launch {
        orderUseCase.clear()
    }

}