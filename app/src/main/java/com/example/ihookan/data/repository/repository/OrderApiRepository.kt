package com.example.ihookan.data.repository.repository

import com.example.ihookan.data.api.ApiClient
import com.example.ihookan.domain.repository.OrderApiCall
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.*

class OrderApiRepository:OrderApiCall {

    override fun insert(name:String, phone:String, description:String, orderPrice:String, orderDate: String) {
        val call = ApiClient.instance?.api?.insert(name, phone, description, orderPrice, orderDate)

        call?.enqueue(object : retrofit2.Callback<ResponseBody?>{
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {

            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                insert(name, phone, description, orderPrice, orderDate)
            }

        })
    }
}