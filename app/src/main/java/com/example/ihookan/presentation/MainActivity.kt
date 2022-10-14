package com.example.ihookan.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ihookan.R
import com.example.ihookan.databinding.ActivityMainBinding
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.OrdersViewModel
import com.example.ihookan.presentation.ViewModel.ProductsViewModel
import com.example.ihookan.presentation.tabs.home.Home
import com.example.ihookan.presentation.tabs.account.Account
import com.example.ihookan.presentation.tabs.basket.Basket
import com.example.ihookan.presentation.tabs.product.Tobacco
import com.example.ihookan.presentation.tabs.register.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val productsViewModel: ProductsViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()
    private val ordersViewModel: OrdersViewModel by viewModel()
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        val user = auth!!.currentUser
        val email = auth!!.currentUser?.email
        if (user != null) {
            productsViewModel.productsMigration(this)
            ordersViewModel.ordersMigration(this, email.toString())
        }



        supportFragmentManager.beginTransaction().replace(R.id.content, Home()).commit()

        binding?.nameAccount?.text = auth!!.currentUser?.displayName
        binding?.bottomNav?.selectedItemId = R.id.homeItemBottomNav
        getBadge()

        binding?.btnAccount?.setOnClickListener(View.OnClickListener {
            auth!!.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        })


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

    override fun onStart() {
        super.onStart()
        val user = auth!!.currentUser
        if (user == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
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

