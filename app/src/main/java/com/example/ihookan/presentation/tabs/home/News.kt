package com.example.ihookan.presentation.tabs.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ihookan.R
import com.example.ihookan.databinding.NewsBinding


class News : Fragment() {

    private var binding: NewsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NewsBinding.inflate(inflater, container, false)

        return binding?.root
    }

}