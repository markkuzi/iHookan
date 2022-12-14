package com.example.ihookan.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class OrderApiModel(
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("email") @Expose
    var email: String? = null,
    @SerializedName("description") @Expose
    var description: String? = null,
    @SerializedName("orderPrice") @Expose
    var orderPrice: String? = null,
    @SerializedName("orderDate") @Expose
    var orderDate: String? = null

)
