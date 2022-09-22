package com.example.ihookan.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ihookan.data.models.ProductModel


@Database(entities = [ProductModel::class], version = 1)
abstract class ProductsDB:RoomDatabase() {
    abstract val productsDao : ProductsDao

}