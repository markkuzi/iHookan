package com.example.ihookan.presentation.tabs.basket

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.ihookan.R
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.databinding.OrderOwnerBinding
import com.example.ihookan.presentation.MainActivity
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.OrderApiViewModel
import com.example.ihookan.presentation.ViewModel.OrderViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class OrderOwner : BottomSheetDialogFragment() {
    
    private var binding: OrderOwnerBinding? = null
    private val orderViewModel: OrderViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()
    private val orderApiViewModel: OrderApiViewModel by viewModel()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = OrderOwnerBinding.inflate(inflater, container, false)

        binding?.addToOrder?.setOnClickListener(View.OnClickListener {

            basketViewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {

                val totalOrder:Int = it.sumOf<BasketModel> { it.price.toInt() }

                val descriptionOrder:String = it.map { it.name + " - " + it.count + "шт., " + it.price + "руб.;" }.joinToString("\n")

                val dateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"))
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")).toString()

                orderViewModel.startInsert(binding?.enterNameOrder?.text.toString(), binding?.enterPhoneOrder?.text.toString(), descriptionOrder, totalOrder.toString(), dateTime)

                orderApiViewModel.insert(binding?.enterNameOrder?.text.toString(), binding?.enterPhoneOrder?.text.toString(), descriptionOrder, totalOrder.toString(), dateTime)
            })

            basketViewModel.clear()




            dismiss()

            Toast.makeText(context, "ЗАКАЗ СОЗДАН", Toast.LENGTH_SHORT).show()


            activity?.let {
                (it as MainActivity).test()
            }



        })
        
        
        return binding?.root
    }

}