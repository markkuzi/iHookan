package com.example.ihookan.data.repository.repository

import androidx.lifecycle.LiveData
import com.example.ihookan.data.localDB.OrderDao
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.domain.repository.OrderCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderRepository(private val orderDao: OrderDao): OrderCall {
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