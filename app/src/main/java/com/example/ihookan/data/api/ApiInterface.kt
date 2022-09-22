package com.example.ihookan.data.api

import com.example.ihookan.data.models.ProductApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*
import kotlin.collections.ArrayList

interface ApiInterface {
    @GET("loadProducts.php")
    fun loadProductsApi() : Call<ArrayList<ProductApiModel>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insert(
        @Field("name") name : String?,
        @Field("phone") phone : String?,
        @Field("description") description : String?,
        @Field("orderPrice") orderPrice : String?,
        @Field("orderDate") orderDate : String?
    ): Call<ResponseBody?>?

}