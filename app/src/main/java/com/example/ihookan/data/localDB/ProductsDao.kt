package com.example.ihookan.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.ProductModel


@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productModel: ProductModel)

    @Query("SELECT * FROM products_data_table")
    fun loadProducts(): LiveData<List<ProductModel>>

    @Query("SELECT * FROM products_data_table WHERE product_category = :category")
    fun loadProductsToCategory(category:String): LiveData<List<ProductModel>>

    @Query("SELECT * FROM products_data_table WHERE product_id = :idProduct")
    fun loadPrice(idProduct:Int): LiveData<List<ProductModel>>

    @Query("DELETE FROM products_data_table")
    fun clear()

}