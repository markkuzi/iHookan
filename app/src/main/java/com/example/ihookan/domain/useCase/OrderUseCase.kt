package com.example.ihookan.domain.useCase

import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.domain.repository.OrderCall

class OrderUseCase(private val orderCall: OrderCall)  {
    fun insert(orderModel: OrderModel) {
        orderCall.insert(orderModel)
    }

    fun loadOrder(): LiveData<List<OrderModel>> {
        return orderCall.loadOrder()
    }

    suspend fun clear() {
        orderCall.clear()
    }


}