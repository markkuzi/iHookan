package com.example.ihookan.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.ProductModel

@Database(entities = [BasketModel::class], version = 2)
abstract class BasketDB: RoomDatabase() {
    abstract val basketDao : BasketDao

}