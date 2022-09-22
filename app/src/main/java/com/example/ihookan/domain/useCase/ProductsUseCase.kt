package com.example.ihookan.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.domain.repository.ProductsCall

class ProductsUseCase(private val productsCall: ProductsCall) {


    fun loadProducts(): LiveData<List<ProductModel>> {
        return productsCall.loadProducts()
    }

    fun loadProductToCategory(category:String): LiveData<List<ProductModel>> {
        return productsCall.loadProductsToCategory(category)
    }

    fun loadPrice(idProduct:Int): LiveData<List<ProductModel>> {
        return productsCall.loadPrice(idProduct)
    }

    suspend fun startProductsMigration(context: Context) {
        productsCall.startProductsMigration(context)
    }

}