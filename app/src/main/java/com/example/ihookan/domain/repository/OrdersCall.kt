package com.example.ihookan.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.OrderModel

interface OrdersCall {

    fun insert(orderModel: OrderModel)

    fun loadOrders(): LiveData<List<OrderModel>>

    suspend fun clear()

    suspend fun startOrdersMigration(context: Context, emailOrder: String)

}