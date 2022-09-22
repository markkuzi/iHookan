package com.example.ihookan.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ihookan.data.models.OrderModel

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderModel: OrderModel)

    @Query("SELECT * FROM order_data_table")
    fun loadOrder(): LiveData<List<OrderModel>>

    @Query("DELETE FROM order_data_table")
    suspend fun clear()

}