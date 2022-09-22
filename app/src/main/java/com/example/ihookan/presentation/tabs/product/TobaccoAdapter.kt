package com.example.ihookan.presentation.tabs.product

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TobaccoAdapter(fragmentActivity: Tobacco) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                SimpleTobacco()
            }
            1 -> {
                MixTobacco()
            }
            2 -> {
                Drinks()
            }
            else -> {
                SimpleTobacco()
            }
        }
    }
}