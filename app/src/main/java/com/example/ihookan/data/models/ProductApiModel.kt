package com.example.ihookan.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductApiModel(
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("name") @Expose
    var name: String? = null,
    @SerializedName("description") @Expose
    var description: String? = null,
    @SerializedName("image") @Expose
    var image: String? = null,
    @SerializedName("price") @Expose
    var price: String? = null,
    @SerializedName("category") @Expose
    var category: String? = null


)
