package com.example.ihookan.domain.useCase

import com.example.ihookan.domain.repository.OrdersApiCall

class OrdersApiUseCase(private val orderApiCall: OrdersApiCall) {

    fun insert(email:String, description:String, orderPrice:String, orderDate: String) {
        orderApiCall.insert(email, description, orderPrice, orderDate)
    }
}