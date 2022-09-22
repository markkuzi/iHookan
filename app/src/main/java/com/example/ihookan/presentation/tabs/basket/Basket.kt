package com.example.ihookan.presentation.tabs.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.databinding.BasketBinding
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.ProductsViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class Basket : Fragment() {

    private var binding : BasketBinding? = null
    private var basketAdapter: BasketAdapter? = null
    private val productsViewModel: ProductsViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketBinding.inflate(inflater, container, false)

        loadCategories()
        initRecyclerProducts()
        totalPrice()



        binding?.addToOrder?.setOnClickListener(View.OnClickListener {
            val orderOwner = OrderOwner()
            orderOwner.show(parentFragmentManager, "owner")
        })

        binding?.clearBasket?.setOnClickListener(View.OnClickListener {
            basketViewModel.clear()
        })


        return binding?.root
    }

    private fun initRecyclerProducts() {
        binding?.listBasket?.layoutManager = LinearLayoutManager(context)
        OverScrollDecoratorHelper.setUpOverScroll(binding?.listBasket, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
        basketAdapter = BasketAdapter ({ basketModel: BasketModel ->
            deleteProductFromBasket(
                basketModel
            )
        }, { basketModel: BasketModel ->
            countAddProduct(
                basketModel
            )
        },  { basketModel: BasketModel ->
            countDelProduct(
                basketModel
            )
        }
            , { basketModel: BasketModel, btnAdd: Button, btnClear: Button ->
            changeBtn(
                basketModel, btnAdd, btnClear
            )}
        )
        binding?.listBasket?.adapter = basketAdapter

    }

    private fun loadCategories() {
        basketViewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {
            basketAdapter?.setList(it)
            basketAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteProductFromBasket(basketModel: BasketModel){
        basketViewModel.deleteProductFromBasket(basketModel.id)
    }

    private fun countAddProduct(basketModel: BasketModel){
        val addCount = (basketModel.count.toInt() + 1).toString()

        productsViewModel.loadPrice(basketModel.idProduct.toInt()).observe(viewLifecycleOwner, Observer {
            val price = it[0].price.toInt()
            val lessPrice = (price * addCount.toInt()).toString()
            basketViewModel.startUpdate(basketModel.id, basketModel.name, basketModel.image, lessPrice, basketModel.idProduct, addCount)
    })
    }

    private fun countDelProduct(basketModel: BasketModel){
        val lessCount = (basketModel.count.toInt() - 1).toString()

        productsViewModel.loadPrice(basketModel.idProduct.toInt()).observe(viewLifecycleOwner, Observer {
            val price = it[0].price.toInt()
            val lessPrice = (price * lessCount.toInt()).toString()
            basketViewModel.startUpdate(basketModel.id, basketModel.name, basketModel.image, lessPrice, basketModel.idProduct, lessCount)
        })
    }

    private fun changeBtn(basketModel: BasketModel, btnLess: Button, btnMore: Button){

        when(basketModel.count.toInt()){
            1 -> btnLess.visibility = View.GONE
            99 -> btnMore.visibility = View.GONE
            else -> {btnLess.visibility = View.VISIBLE
                    btnMore.visibility = View.VISIBLE}
        }


    }

    private fun totalPrice(){
        val basketList = ArrayList<BasketModel>()
        basketViewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {
            basketList.clear()
            basketList.addAll(it)
            var totalPrice = 0
            for (product in basketList){
                totalPrice += product.price.toInt()
            }
            binding?.totalPrice?.text = totalPrice.toString()
        })


    }
}