package com.example.ihookan.data.repository.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.example.ihookan.data.localDB.ProductsDao
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.data.repository.dataSource.ProductsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsDataSourceIMPL (private val productsDao: ProductsDao):
ProductsDataSource{

    override fun insert(productModel: ProductModel) {
        CoroutineScope(Dispatchers.IO).launch {
            productsDao.insert(productModel)
        }
    }

    override fun loadProducts(): LiveData<List<ProductModel>> {
        return productsDao.loadProducts()
    }

    override fun loadProductsToCategory(category: String): LiveData<List<ProductModel>> {
        return productsDao.loadProductsToCategory(category)
    }

    override fun loadPrice(idProduct:Int): LiveData<List<ProductModel>> {
        return productsDao.loadPrice(idProduct)
    }

    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            productsDao.clear()
        }
    }

}