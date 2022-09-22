package com.example.ihookan.presentation.tabs.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.databinding.ProductItemTestBinding
import com.squareup.picasso.Picasso

class ProductsAdapter(private val addToBasket:(ProductModel)->Unit,
                      private val deleteFromCardProduct:(ProductModel)->Unit,
                      private val changeBtn:(Int, Button, Button)->Unit,
                      private val showDetails:(ProductModel)->Unit)
    : RecyclerView.Adapter<ProductsAdapter.ProductsHolder>() {

    private val productsList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding = ProductItemTestBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(productsList[position], addToBasket, deleteFromCardProduct, changeBtn, showDetails)
    }

    fun setList(categories: List<ProductModel>) {
        productsList.clear()
        productsList.addAll(categories)

    }



    class ProductsHolder(private val binding: ProductItemTestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            productModel: ProductModel,
            addToBasket: (ProductModel) -> Unit,
            deleteFromCardProduct: (ProductModel) -> Unit,
            changeBtn: (Int, Button, Button) -> Unit,
            showDetails: (ProductModel) -> Unit
        ){
            binding.nameProduct.text =productModel.name
            //binding.descriptionProduct.text = productModel.description
            binding.priceProduct.text = productModel.price

            Picasso.get().load(productModel.image).into(binding.imageProduct)

            binding.btnAdd.setOnClickListener(View.OnClickListener {
                addToBasket(productModel)
            })

            binding.btnClear.setOnClickListener(View.OnClickListener {
                deleteFromCardProduct(productModel)
            })

            changeBtn(productModel.id, binding.btnAdd, binding.btnClear)

            binding.cardCategory.setOnClickListener(View.OnClickListener {
                showDetails(productModel)
            })



        }
    }
}