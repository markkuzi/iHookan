package com.example.ihookan.presentation.tabs.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.ihookan.data.models.BasketModel
import com.example.ihookan.databinding.OrderOwnerBinding
import com.example.ihookan.presentation.MainActivity
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.OrdersApiViewModel
import com.example.ihookan.presentation.ViewModel.OrdersViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class OrderOwner : BottomSheetDialogFragment() {
    
    private var binding: OrderOwnerBinding? = null
    private val ordersViewModel: OrdersViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()
    private val ordersApiViewModel: OrdersApiViewModel by viewModel()
    var auth: FirebaseAuth? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        
        binding = OrderOwnerBinding.inflate(inflater, container, false)

        binding?.addToOrder?.setOnClickListener(View.OnClickListener {

            basketViewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {


                val date = getCurrentDateTime()
                val dateNowInString = date.toString("dd.MM.yyyy, HH:mm")

                val totalOrder:Int = it.sumOf<BasketModel> { it.price.toInt() }


                val descriptionOrder:String = it.map { it.name + " - " + it.count + "шт., " + it.price + "руб.;" }.joinToString("\n")

                ordersViewModel.startInsert(auth!!.currentUser?.email.toString(), descriptionOrder, totalOrder.toString(), dateNowInString)

                ordersApiViewModel.insert(auth!!.currentUser?.email.toString(), descriptionOrder, totalOrder.toString(), dateNowInString)
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

    fun Date.toString(format: String, locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getTimeZone("Europe/Moscow")): String {
        val formatter = SimpleDateFormat(format, locale)
        formatter.timeZone = timeZone
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}