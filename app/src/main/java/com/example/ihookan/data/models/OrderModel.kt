package com.example.ihookan.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_data_table")
class OrderModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    val id:Int,

    @ColumnInfo(name = "order_email")
    val email:String,

    @ColumnInfo(name = "order_description")
    val description:String,

    @ColumnInfo(name = "order_price")
    val price:String,

    @ColumnInfo(name = "order_date")
    val orderDate:String


)