package com.example.ihookan.domain.useCase

import com.example.ihookan.domain.repository.OrderApiCall
import java.util.*

class OrderApiUseCase(private val orderApiCall: OrderApiCall) {

    suspend fun insert(name:String, phone:String, description:String, orderPrice:String, orderDate: String) {
        orderApiCall.insert(name, phone, description, orderPrice, orderDate)
    }
}