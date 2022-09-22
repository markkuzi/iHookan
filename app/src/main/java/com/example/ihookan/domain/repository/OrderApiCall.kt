package com.example.ihookan.domain.repository

import java.util.*

interface OrderApiCall {

    fun insert(name:String, phone:String, description:String, orderPrice:String, orderDate: String)
}