package com.example.ihookan.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_data_table")
class BasketModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "basket_id")
    val id:Int,

    @ColumnInfo(name = "basket_name")
    val name:String,

    @ColumnInfo(name = "basket_image")
    val image:String,

    @ColumnInfo(name = "basket_price")
    val price:String,

    @ColumnInfo(name = "basket_id_product")
    val idProduct:String,

    @ColumnInfo(name = "basket_count")
    val count:String



)