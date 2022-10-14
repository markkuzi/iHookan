package com.example.ihookan.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.domain.repository.OrdersCall

class OrdersUseCase(private val ordersCall: OrdersCall)  {
    fun insert(orderModel: OrderModel) {
        ordersCall.insert(orderModel)
    }

    fun loadOrders(): LiveData<List<OrderModel>> {
        return ordersCall.loadOrders()
    }

    suspend fun clear() {
        ordersCall.clear()
    }

    suspend fun startOrdersMigration(context: Context, emailOrder: String){
        ordersCall.startOrdersMigration(context, emailOrder)
    }


}