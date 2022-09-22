package com.example.ihookan.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products_data_table")
class ProductModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "product_id")
    val id:Int,

    @ColumnInfo(name = "product_name")
    val name:String,

    @ColumnInfo(name = "product_description")
    val description:String,

    @ColumnInfo(name = "product_image")
    val image:String,

    @ColumnInfo(name = "product_price")
    val price:String,

    @ColumnInfo(name = "product_category")
    val category:String


)
