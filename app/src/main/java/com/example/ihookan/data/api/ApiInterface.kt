package com.example.ihookan.data.api

import com.example.ihookan.data.models.OrderApiModel
import com.example.ihookan.data.models.ProductApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface ApiInterface {
    @GET("loadProducts.php")
    fun loadProductsApi() : Call<ArrayList<ProductApiModel>>

    @FormUrlEncoded
    @POST("insertOrder.php")
    fun insert(
        @Field("email") email : String?,
        @Field("description") description : String?,
        @Field("orderPrice") orderPrice : String?,
        @Field("orderDate") orderDate : String?
    ): Call<ResponseBody?>?

    @GET("loadOrders.php")
    fun loadOrdersApi(
        @Query("emailOrder") emailOrder : String?
    ) : Call<ArrayList<OrderApiModel>>

}