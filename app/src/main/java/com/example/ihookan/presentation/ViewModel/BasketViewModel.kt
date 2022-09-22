package com.example.ihookan.presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.domain.useCase.BasketUseCase
import kotlinx.coroutines.launch

class BasketViewModel(private val basketUseCase: BasketUseCase): ViewModel() {


    fun startInsert(nameProduct:String, imageProduct:String, priceProduct:String, idProduct: String, countProduct:String) {
        insert(
            BasketModel(0, nameProduct,imageProduct,priceProduct,idProduct,countProduct)
        )
    }

    fun startUpdate(id:Int,nameProduct:String, imageProduct:String, priceProduct:String, idProduct: String, countProduct:String) {
        update(
            BasketModel(id,nameProduct,imageProduct,priceProduct,idProduct,countProduct)
        )
    }


    private fun insert(basketModel: BasketModel) = viewModelScope.launch {
        basketUseCase.insert(basketModel)
    }

    private fun update(basketModel: BasketModel) = viewModelScope.launch {
        basketUseCase.update(basketModel)
    }

    val loadProductsFromBasket = basketUseCase.loadProductsFromBasket()

    fun loadProductsToBasketFromBasketProducts(idProduct: String): LiveData<List<BasketModel>> {
        return basketUseCase.loadProductsToBasketFromBasketProducts(idProduct)
    }

    fun deleteProductFromBasket(idProductToBasket: Int) = viewModelScope.launch {
        basketUseCase.deleteProductFromBasket(idProductToBasket)
    }

    fun deleteProductToBasketFromBasketProduct(idProduct: String) = viewModelScope.launch {
        basketUseCase.deleteProductToBasketFromBasketProduct(idProduct)
    }

    fun clear() = viewModelScope.launch {
        basketUseCase.clear()
    }
}