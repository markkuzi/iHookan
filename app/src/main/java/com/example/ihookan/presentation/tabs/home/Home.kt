package com.example.ihookan.presentation.tabs.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.ihookan.databinding.HomeBinding
import com.example.ihookan.presentation.tabs.product.TobaccoAdapter
import com.google.android.material.tabs.TabLayoutMediator


class Home : Fragment() {

    private var binding:HomeBinding? = null



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = HomeBinding.inflate(inflater, container, false)

        binding?.sliderTabs?.adapter = HomeAdapter(this)


        var tabLayoutMediatorTabs = binding?.tabSlider?.let {
            binding?.sliderTabs?.let { it1 ->
                TabLayoutMediator(it, it1,
                    TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                        when(position) {
                            0 -> {
                                tab.text = "Новости"
                            }
                            1 -> {
                                tab.text = "Микс дня"
                            }


                        }
                    })
            }
        }

        tabLayoutMediatorTabs?.attach()







        return binding?.root
    }

}