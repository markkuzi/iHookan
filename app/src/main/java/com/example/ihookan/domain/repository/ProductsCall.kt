package com.example.ihookan.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.ProductModel

interface ProductsCall {

    fun loadProducts(): LiveData<List<ProductModel>>

    fun loadPrice(idProduct:Int): LiveData<List<ProductModel>>

    fun loadProductsToCategory(category:String): LiveData<List<ProductModel>>

    suspend fun startProductsMigration(context: Context)

}