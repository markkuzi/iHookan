package com.example.ihookan.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.ProductModel

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(basketModel: BasketModel)

    @Update
    fun update(basketModel: BasketModel)

    @Query("SELECT * FROM basket_data_table")
    fun loadProductsFromBasket(): LiveData<List<BasketModel>>

    @Query("SELECT * FROM basket_data_table WHERE basket_id_product = :idProduct")
    fun loadProductsToBasketFromBasketProducts(idProduct:String): LiveData<List<BasketModel>>

    @Query("DELETE FROM basket_data_table WHERE basket_id = :idProductToBasket")
    suspend fun deleteProductFromBasket(idProductToBasket:Int)

    @Query("DELETE FROM basket_data_table WHERE basket_id_product = :idProduct")
    suspend fun deleteProductToBasketFromBasketProduct(idProduct:String)

    @Query("DELETE FROM basket_data_table")
    suspend fun clear()

}