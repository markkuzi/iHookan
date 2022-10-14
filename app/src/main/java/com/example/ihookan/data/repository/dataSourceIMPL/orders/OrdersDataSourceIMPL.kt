package com.example.ihookan.data.repository.dataSourceIMPL.orders

import androidx.lifecycle.LiveData
import com.example.ihookan.data.localDB.OrderDao
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.data.repository.dataSource.orders.OrdersDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersDataSourceIMPL(private val orderDao: OrderDao): OrdersDataSource {
    override fun insert(orderModel: OrderModel) {
        CoroutineScope(Dispatchers.IO).launch {
            orderDao.insert(orderModel)
        }}

    override fun loadOrder(): LiveData<List<OrderModel>> {
        return orderDao.loadOrder()
    }

    override suspend fun clear() {
        orderDao.clear()
    }


}