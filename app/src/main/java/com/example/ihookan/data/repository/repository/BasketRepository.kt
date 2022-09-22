package com.example.ihookan.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.ihookan.data.localDB.BasketDao
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.data.repository.dataSource.ProductsApiDataSource
import com.example.ihookan.data.repository.dataSource.ProductsDataSource
import com.example.ihookan.domain.repository.BasketCall
import com.example.ihookan.domain.repository.ProductsCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketRepository(private val basketDao: BasketDao
): BasketCall {


    override fun insert(basketModel: BasketModel) {
        CoroutineScope(Dispatchers.IO).launch {
        basketDao.insert(basketModel)
    }
}
    override fun update(basketModel: BasketModel) {
        CoroutineScope(Dispatchers.IO).launch {
            basketDao.update(basketModel)
        }
    }

    override fun loadProductsFromBasket(): LiveData<List<BasketModel>> {
        return basketDao.loadProductsFromBasket()
    }

    override fun loadProductsToBasketFromBasketProducts(idProduct: String): LiveData<List<BasketModel>> {
        return basketDao.loadProductsToBasketFromBasketProducts(idProduct)
    }

    override suspend fun deleteProductFromBasket(idProductToBasket: Int) {
        basketDao.deleteProductFromBasket(idProductToBasket)
    }

    override suspend fun deleteProductToBasketFromBasketProduct(idProduct: String) {
        basketDao.deleteProductToBasketFromBasketProduct(idProduct)
    }

    override suspend fun clear() {
        basketDao.clear()
    }


}