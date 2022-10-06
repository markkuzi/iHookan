package com.example.ihookan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ihookan.R
import com.example.ihookan.databinding.ActivityMainBinding
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.ProductsViewModel
import com.example.ihookan.presentation.tabs.home.Home
import com.example.ihookan.presentation.tabs.account.Account
import com.example.ihookan.presentation.tabs.basket.Basket
import com.example.ihookan.presentation.tabs.product.Tobacco
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val productsViewModel: ProductsViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()
    private var viewForSnackbar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        productsViewModel.productsMigration(this)

        supportFragmentManager.beginTransaction().replace(R.id.content, Home()).commit()

        binding?.bottomNav?.selectedItemId = R.id.homeItemBottomNav
        getBadge()



        binding?.bottomNav?.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.homeItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, Home()).commit()
                R.id.hookahItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, Tobacco()).commit()
                R.id.basketItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, Basket()).commit()
                R.id.accountItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, Account()).commit()
            }

            return@setOnItemSelectedListener true

        }


    }

    private fun getBadge() {
        val badge = binding?.bottomNav?.getOrCreateBadge(R.id.basketItemBottomNav)

        basketViewModel.loadProductsFromBasket.observe({ lifecycle }, {
            when(it.size){
                0 -> badge?.isVisible = false
                else -> {
                    badge?.isVisible = true
                    badge?.number = it.size
                }
            }

        })
    }

    fun test(){
        binding?.bottomNav?.selectedItemId = R.id.accountItemBottomNav
    }
}

