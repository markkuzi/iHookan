package com.example.ihookan.presentation.tabs.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ihookan.databinding.AccountBinding
import com.example.ihookan.presentation.ViewModel.OrdersViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.androidx.viewmodel.ext.android.viewModel


class Account : Fragment() {

    private var binding: AccountBinding? = null
    private var orderAdapter: OrderAdapter? = null
    private val ordersViewModel: OrdersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AccountBinding.inflate(inflater, container, false)

        loadCategories()
        initRecyclerProducts()

        binding?.clearOrder?.setOnClickListener(View.OnClickListener {
            ordersViewModel.clear()
        })


        return binding?.root

    }

    private fun initRecyclerProducts() {
        binding?.orderList?.layoutManager = LinearLayoutManager(context)
        OverScrollDecoratorHelper.setUpOverScroll(binding?.orderList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
        orderAdapter = OrderAdapter()
        binding?.orderList?.adapter = orderAdapter

    }

    private fun loadCategories() {
        ordersViewModel.loadOrders.observe(viewLifecycleOwner, Observer {
            orderAdapter?.setList(it)
            orderAdapter?.notifyDataSetChanged()
        })

}}