package com.example.ihookan.presentation.tabs.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.ihookan.databinding.HomeBinding


class Home : Fragment() {

    private var binding:HomeBinding? = null



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = HomeBinding.inflate(inflater, container, false)








        return binding?.root
    }

}