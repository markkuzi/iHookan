package com.example.ihookan.domain.repository

import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.OrderModel

interface OrderCall {

    fun insert(orderModel: OrderModel)

    fun loadOrder(): LiveData<List<OrderModel>>

    suspend fun clear()


}