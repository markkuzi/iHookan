package com.example.ihookan.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.ProductModel

interface ProductsDataSource {

    fun insert(productModel: ProductModel)

    fun loadProducts(): LiveData<List<ProductModel>>

    fun loadProductsToCategory(category:String): LiveData<List<ProductModel>>

    fun loadPrice(idProduct:Int): LiveData<List<ProductModel>>

    suspend fun clear()

}