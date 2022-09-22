package com.example.ihookan.data.repository.dataSourceIMPL

import android.content.Context
import android.widget.Toast
import com.example.ihookan.data.api.ApiClient
import com.example.ihookan.data.models.ProductApiModel
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.data.repository.dataSource.ProductsApiDataSource
import com.example.ihookan.data.repository.dataSource.ProductsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsApiDataSourceIMPL(private val productsDataSource: ProductsDataSource):
ProductsApiDataSource{

    override fun startProductsMigration(context: Context) {

        val call = ApiClient.instance?.api?.loadProductsApi()
        call?.enqueue(object: Callback<ArrayList<ProductApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<ProductApiModel>>,
                response: Response<ArrayList<ProductApiModel>>
            ) {
                var loadProducts: ArrayList<ProductApiModel>? = null

                loadProducts?.clear()

                loadProducts = (response.body() as ArrayList<ProductApiModel>?)!!

                for (audit in loadProducts) {

                    audit.id?.let {
                        ProductModel(
                            it,
                            audit.name.toString(),
                            audit.description.toString(),
                            audit.image.toString(),
                            audit.price.toString(),
                            audit.category.toString()
                        ).let {
                            productsDataSource.insert(
                                it
                            )
                        }
                    }
                }
                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<ProductApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()
            }
        } )



    }

}