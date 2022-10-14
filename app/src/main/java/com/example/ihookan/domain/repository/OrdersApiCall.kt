package com.example.ihookan.domain.repository


interface OrdersApiCall {

    fun insert(email:String, description:String, orderPrice:String, orderDate: String)
}