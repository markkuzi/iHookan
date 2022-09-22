package com.example.ihookan.presentation.tabs.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.ihookan.databinding.DetailsProductBinding
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsProduct : BottomSheetDialogFragment() {

    private var binding : DetailsProductBinding? = null
    private val basketViewModel: BasketViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsProductBinding.inflate(inflater, container, false)


        val id:String = arguments?.getString("id").toString()
        val name:String = arguments?.getString("name").toString()
        val description:String = arguments?.getString("description").toString()
        val image:String =arguments?.getString("image").toString()
        val price: String = arguments?.getString("price").toString()



        binding?.nameProduct?.text = name
        binding?.descriptionProduct?.text = description
        binding?.priceProduct?.text = price

        Picasso.get().load(image).into(binding?.imageProduct)

        binding?.btnAdd?.setOnClickListener(View.OnClickListener {
            addToBasket(name, image, price, id)
        })

        binding?.btnClear?.setOnClickListener(View.OnClickListener {
            deleteFromCardProduct(id)
        })


        changeBtn(id)




        return binding?.root
    }

    private fun addToBasket(name:String, image: String, price:String, idProduct:String){
        basketViewModel.startInsert(name, image, price, idProduct, "1")
    }

    private fun deleteFromCardProduct(idProduct: String){
        basketViewModel.deleteProductToBasketFromBasketProduct(idProduct)
    }

    private fun changeBtn(idProduct:String){
        basketViewModel.loadProductsToBasketFromBasketProducts(idProduct).observe(viewLifecycleOwner, Observer {
            val count = it.count()

            if (count > 0) {
                binding?.btnAdd?.visibility = View.GONE
                binding?.btnClear?.visibility = View.VISIBLE
            }
            else {
                binding?.btnAdd?.visibility = View.VISIBLE
                binding?.btnClear?.visibility = View.GONE
            }
        })
    }


}