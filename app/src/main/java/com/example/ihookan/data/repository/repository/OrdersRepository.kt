package com.example.ihookan.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.data.repository.dataSource.orders.OrdersApiDataSource
import com.example.ihookan.data.repository.dataSource.orders.OrdersDataSource
import com.example.ihookan.domain.repository.OrdersCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersRepository(private val ordersDataSource: OrdersDataSource,
private val ordersApiDataSource: OrdersApiDataSource): OrdersCall {
    override fun insert(orderModel: OrderModel) {
        CoroutineScope(Dispatchers.IO).launch {
            ordersDataSource.insert(orderModel)
    }}

    override fun loadOrders(): LiveData<List<OrderModel>> {
        return ordersDataSource.loadOrder()
    }

    override suspend fun clear() {
        ordersDataSource.clear()
    }

    override suspend fun startOrdersMigration(context: Context, emailOrder: String) {
        ordersDataSource.clear()
        ordersApiDataSource.startOrdersMigration(context, emailOrder)
    }


}