package com.example.ihookan.domain.useCase

import androidx.lifecycle.LiveData
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.domain.repository.BasketCall

class BasketUseCase(private val basketCall:BasketCall) {

    fun insert(basketModel: BasketModel) {
        basketCall.insert(basketModel)
    }

    fun update(basketModel: BasketModel) {
        basketCall.update(basketModel)
    }

    fun loadProductsFromBasket(): LiveData<List<BasketModel>> {
        return basketCall.loadProductsFromBasket()
    }

    fun loadProductsToBasketFromBasketProducts(idProduct: String): LiveData<List<BasketModel>> {
        return basketCall.loadProductsToBasketFromBasketProducts(idProduct)
    }

    suspend fun deleteProductFromBasket(idProductToBasket: Int) {
        basketCall.deleteProductFromBasket(idProductToBasket)
    }

    suspend fun deleteProductToBasketFromBasketProduct(idProduct: String) {
        basketCall.deleteProductToBasketFromBasketProduct(idProduct)
    }

    suspend fun clear() {
        basketCall.clear()
    }
}