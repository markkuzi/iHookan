package com.example.ihookan.data.repository.dataSourceIMPL.orders

import android.content.Context
import android.widget.Toast
import com.example.ihookan.data.api.ApiClient
import com.example.ihookan.data.models.OrderApiModel
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.data.repository.dataSource.orders.OrdersApiDataSource
import com.example.ihookan.data.repository.dataSource.orders.OrdersDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersApiDataSourceIMPL(private val ordersDataSource: OrdersDataSource): OrdersApiDataSource {

    override fun startOrdersMigration(context: Context, emailOrder: String) {

        val call = ApiClient.instance?.api?.loadOrdersApi(emailOrder)
        call?.enqueue(object: Callback<ArrayList<OrderApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<OrderApiModel>>,
                response: Response<ArrayList<OrderApiModel>>
            ) {
                var loadOrders: ArrayList<OrderApiModel>? = null
                loadOrders?.clear()
                loadOrders = (response.body() as ArrayList<OrderApiModel>?)!!

                for (audit in loadOrders) {
                    audit.id?.let{
                        OrderModel(
                            it,
                            audit.email.toString(),
                            audit.description.toString(),
                            audit.orderPrice.toString(),
                            audit.orderDate.toString()
                        ).let {
                            ordersDataSource.insert(
                                it
                            )
                        }
                    }
                }
                Toast.makeText(context, "ЗАГРУЗКА ЗАКАЗОВ", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ArrayList<OrderApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()
            }

        })

    }
}