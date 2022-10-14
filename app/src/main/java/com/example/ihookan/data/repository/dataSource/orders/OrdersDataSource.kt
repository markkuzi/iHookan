package com.example.ihookan.data.repository.dataSource.orders

import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.OrderModel

interface OrdersDataSource {

    fun insert(orderModel: OrderModel)

    fun loadOrder(): LiveData<List<OrderModel>>

    suspend fun clear()


}