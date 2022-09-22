package com.example.ihookan.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ihookan.data.models.OrderModel

@Database(entities = [OrderModel::class], version = 1)
abstract class OrderDB: RoomDatabase() {
    abstract val orderDao :OrderDao

}