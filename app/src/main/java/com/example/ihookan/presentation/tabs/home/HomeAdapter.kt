package com.example.ihookan.presentation.tabs.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ihookan.presentation.tabs.product.Drinks
import com.example.ihookan.presentation.tabs.product.MixTobacco
import com.example.ihookan.presentation.tabs.product.SimpleTobacco
import com.example.ihookan.presentation.tabs.product.Tobacco

class HomeAdapter(fragmentActivity: Home) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                News()
            }
            1 -> {
                Promotion()
            }
            else -> {
                News()
            }
        }
    }
}