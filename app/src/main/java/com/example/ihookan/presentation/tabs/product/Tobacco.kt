package com.example.ihookan.presentation.tabs.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ihookan.databinding.TobaccoBinding
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.ProductsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class Tobacco : Fragment() {

    private var binding: TobaccoBinding? = null
    private var productsAdapter: ProductsAdapter? = null
    private val productsViewModel: ProductsViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = TobaccoBinding.inflate(inflater, container, false)
        binding?.sliderTabs?.adapter = TobaccoAdapter(this)


        var tabLayoutMediatorTabs = binding?.tabSlider?.let {
            binding?.sliderTabs?.let { it1 ->
                TabLayoutMediator(it, it1,
                    TabLayoutMediator.TabConfigurationStrategy {tab, position ->
                        when(position) {
                            0 -> {
                                tab.text = "Табак"
                            }
                            1 -> {
                                tab.text = "Миксы"
                            }
                            2 -> {
                                tab.text = "Напитки"
                            }


                        }
                    })
            }
        }

        tabLayoutMediatorTabs?.attach()


        return binding?.root
    }

//    private fun initRecyclerProducts() {
//        binding?.productsList?.layoutManager = LinearLayoutManager(context)
//        OverScrollDecoratorHelper.setUpOverScroll(binding?.productsList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
//        productsAdapter = ProductsAdapter({ productModel: ProductModel ->
//            addToBasket(
//                productModel
//            )
//        }, { productModel: ProductModel ->
//            deleteFromCardProduct(
//                productModel
//            )}, { idProduct:Int, btnAdd: Button, btnClear: Button ->
//            changeBtn(
//                idProduct, btnAdd, btnClear
//            )})
//        binding?.productsList?.adapter = productsAdapter
//
//    }
//
//    private fun loadCategories() {
//        productsViewModel.loadProducts.observe(viewLifecycleOwner, Observer {
//            productsAdapter?.setList(it)
//            productsAdapter?.notifyDataSetChanged()
//        })
//
//
//    }
//
//    private fun addToBasket(productModel: ProductModel){
//        basketViewModel.startInsert(productModel.name,productModel.image, productModel.price, productModel.id.toString(), "1")
//    }
//
//    private fun deleteFromCardProduct(productModel: ProductModel){
//        basketViewModel.deleteProductToBasketFromBasketProduct(productModel.id.toString())
//    }
//
//    private fun changeBtn(idProduct:Int, btnAdd: Button, btnClear: Button){
//        basketViewModel.loadProductsToBasketFromBasketProducts(idProduct.toString()).observe(viewLifecycleOwner, Observer {
//            val count = it.count()
//
//            if (count > 0) {
//                btnAdd.visibility = View.GONE
//                btnClear.visibility = View.VISIBLE
//            }
//            else {
//                btnAdd.visibility = View.VISIBLE
//                btnClear.visibility = View.GONE
//            }
//        })
//    }



}