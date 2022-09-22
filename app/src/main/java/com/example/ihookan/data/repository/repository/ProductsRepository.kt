package com.example.ihookan.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.data.repository.dataSource.ProductsApiDataSource
import com.example.ihookan.data.repository.dataSource.ProductsDataSource
import com.example.ihookan.domain.repository.ProductsCall

class ProductsRepository(private val productsDataSource: ProductsDataSource,
private val productsApiDataSource: ProductsApiDataSource
): ProductsCall {

    override fun loadProducts(): LiveData<List<ProductModel>> {
        return productsDataSource.loadProducts()
    }

    override fun loadPrice(idProduct:Int): LiveData<List<ProductModel>>{
        return productsDataSource.loadPrice(idProduct)
    }

    override fun loadProductsToCategory(category: String): LiveData<List<ProductModel>> {
        return productsDataSource.loadProductsToCategory(category)
    }

    override suspend fun startProductsMigration(context: Context) {
        productsDataSource.clear()
        productsApiDataSource.startProductsMigration(context)
    }


}