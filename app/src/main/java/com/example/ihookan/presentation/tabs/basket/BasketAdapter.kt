package com.example.ihookan.presentation.tabs.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.databinding.BasketItemBinding
import com.squareup.picasso.Picasso

class BasketAdapter(private val deleteProductFromBasket:(BasketModel)->Unit,
                    private val countAddProduct:(BasketModel)->Unit,
                    private val countDelProduct:(BasketModel)->Unit,
                    private val changeBtn:(BasketModel, Button, Button)->Unit
): RecyclerView.Adapter<BasketAdapter.ProductsHolder>() {

    private val basketList = ArrayList<BasketModel>()
    private val productList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding = BasketItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(basketList[position], deleteProductFromBasket, countAddProduct, countDelProduct, changeBtn)
    }

    fun setList(categories: List<BasketModel>) {
        basketList.clear()
        basketList.addAll(categories)

    }

    class ProductsHolder(private val binding: BasketItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            basketModel: BasketModel,
            deleteProductFromBasket: (BasketModel) -> Unit,
            countAddProduct: (BasketModel) -> Unit,
            countDelProduct: (BasketModel) -> Unit
            ,
            changeBtn: (BasketModel, Button, Button) -> Unit
        ){
            binding.nameProduct.text =basketModel.name
            binding.countProduct.text = basketModel.count
            binding.priceProduct.text = basketModel.price
            Picasso.get().load(basketModel.image).into(binding.imageProduct)

            binding.btnClear.setOnClickListener(View.OnClickListener {
                deleteProductFromBasket(basketModel)

            })

            binding.btnMore.setOnClickListener((View.OnClickListener {
                countAddProduct(basketModel)
            }))

            binding.btnLess.setOnClickListener((View.OnClickListener {
                countDelProduct(basketModel)
            }))

            changeBtn(basketModel, binding.btnLess, binding.btnMore)

        }
    }
}