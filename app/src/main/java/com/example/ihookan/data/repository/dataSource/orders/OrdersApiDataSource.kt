package com.example.ihookan.data.repository.dataSource.orders

import android.content.Context

interface OrdersApiDataSource {

    fun startOrdersMigration(context: Context, emailOrder: String)

}