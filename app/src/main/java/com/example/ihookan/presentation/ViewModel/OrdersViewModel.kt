package com.example.ihookan.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.domain.useCase.OrdersUseCase
import kotlinx.coroutines.launch

class OrdersViewModel(private val orderUseCase: OrdersUseCase): ViewModel() {

    fun startInsert(emailOrder:String, descriptionOrder:String, priceOrder: String, orderDate:String) {
        insert(
            OrderModel(0, emailOrder, descriptionOrder, priceOrder, orderDate)
        )
    }

    private fun insert(orderModel: OrderModel) = viewModelScope.launch {
        orderUseCase.insert(orderModel)
    }

    val loadOrders = orderUseCase.loadOrders()


    fun clear() = viewModelScope.launch {
        orderUseCase.clear()
    }

    fun ordersMigration(context: Context, emailOrder: String) = viewModelScope.launch {
        orderUseCase.startOrdersMigration(context, emailOrder)
    }

}