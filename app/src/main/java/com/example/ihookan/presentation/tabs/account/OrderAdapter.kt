package com.example.ihookan.presentation.tabs.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ihookan.data.models.OrderModel
import com.example.ihookan.databinding.OrderItemBinding

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    private val orderList = ArrayList<OrderModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val binding = OrderItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orderList[position])
    }

    fun setList(categories: List<OrderModel>) {
        orderList.clear()
        orderList.addAll(categories)

    }

    class OrderHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            orderModel: OrderModel){
            binding.nameOrder.text = orderModel.name
            binding.phoneOrder.text = orderModel.phone
            binding.descriptionOrder.text = orderModel.description
            binding.priceOrder.text = orderModel.price
            binding.orderDate.text = orderModel.orderDate

        }
    }
}