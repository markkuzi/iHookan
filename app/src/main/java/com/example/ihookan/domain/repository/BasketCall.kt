package com.example.ihookan.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.ProductModel

interface BasketCall {

    fun insert(basketModel: BasketModel)

    fun update(basketModel: BasketModel)

    fun loadProductsFromBasket(): LiveData<List<BasketModel>>

    fun loadProductsToBasketFromBasketProducts(idProduct:String): LiveData<List<BasketModel>>

    suspend fun deleteProductFromBasket(idProductToBasket:Int)

    suspend fun deleteProductToBasketFromBasketProduct(idProduct:String)

    suspend fun clear()


}