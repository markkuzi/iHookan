package com.example.ihookan.presentation.tabs.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ihookan.data.models.ProductModel
import com.example.ihookan.databinding.SimpleTobaccoBinding
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.ProductsViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimpleTobacco : Fragment() {

    private var binding: SimpleTobaccoBinding? = null
    private var productsAdapter: ProductsAdapter? = null
    private val productsViewModel: ProductsViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SimpleTobaccoBinding.inflate(inflater, container, false)

        loadCategories()
        initRecyclerProducts()


        return binding?.root
    }

    private fun initRecyclerProducts() {
        binding?.productsList?.layoutManager = GridLayoutManager(context, 2)
        OverScrollDecoratorHelper.setUpOverScroll(binding?.productsList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
        productsAdapter = ProductsAdapter({ productModel: ProductModel ->
            addToBasket(
                productModel
            )
        }, { productModel: ProductModel ->
            deleteFromCardProduct(
                productModel
            )}, { idProduct:Int, btnAdd: Button, btnClear: Button ->
            changeBtn(
                idProduct, btnAdd, btnClear
            )},{ productModel: ProductModel ->
            showDetails(
                productModel
            )
        }
        )
        binding?.productsList?.adapter = productsAdapter

    }

    private fun loadCategories() {
        productsViewModel.loadProductToCategory("Tobacco").observe(viewLifecycleOwner, Observer {
            productsAdapter?.setList(it)
            productsAdapter?.notifyDataSetChanged()
        })


    }

    private fun addToBasket(productModel: ProductModel){
        basketViewModel.startInsert(productModel.name,productModel.image, productModel.price, productModel.id.toString(), "1")
    }

    private fun deleteFromCardProduct(productModel: ProductModel){
        basketViewModel.deleteProductToBasketFromBasketProduct(productModel.id.toString())
    }

    private fun changeBtn(idProduct:Int, btnAdd: Button, btnClear: Button){
        basketViewModel.loadProductsToBasketFromBasketProducts(idProduct.toString()).observe(viewLifecycleOwner, Observer {
            val count = it.count()

            if (count > 0) {
                btnAdd.visibility = View.GONE
                btnClear.visibility = View.VISIBLE
            }
            else {
                btnAdd.visibility = View.VISIBLE
                btnClear.visibility = View.GONE
            }
        })
    }

    private fun showDetails(productModel: ProductModel) {

        val detailsProduct = DetailsProduct()
        val parameters = Bundle()
        parameters.putString("name", productModel.name)
        parameters.putString("description", productModel.description)
        parameters.putString("image", productModel.image)
        parameters.putString("price", productModel.price)
        parameters.putString("id", productModel.id.toString())

        detailsProduct.arguments = parameters
        detailsProduct.show(parentFragmentManager, "details")

    }



}