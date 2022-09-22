package com.example.ihookan.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.domain.useCase.ProductsUseCase
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsUseCase: ProductsUseCase) :
    ViewModel() {

    val loadProducts = productsUseCase.loadProducts()

    fun loadProductToCategory(category:String): LiveData<List<ProductModel>> {
        return productsUseCase.loadProductToCategory(category)
    }

    fun loadPrice(idProduct:Int): LiveData<List<ProductModel>> {
        return productsUseCase.loadPrice(idProduct)
    }

    fun productsMigration(context: Context) = viewModelScope.launch {
        productsUseCase.startProductsMigration(context)
    }
}