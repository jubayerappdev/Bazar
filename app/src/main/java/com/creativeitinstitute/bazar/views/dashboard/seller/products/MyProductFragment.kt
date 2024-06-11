package com.creativeitinstitute.bazar.views.dashboard.seller.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.creativeitinstitute.bazar.R
import com.creativeitinstitute.bazar.base.BaseFragment
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.data.Product
import com.creativeitinstitute.bazar.databinding.FragmentMyProductBinding
import com.creativeitinstitute.bazar.views.dashboard.seller.upload.SellerProductAdapter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProductFragment : BaseFragment<FragmentMyProductBinding>(FragmentMyProductBinding::inflate) {

    private val viewModel:ProductViewModel by viewModels()
    override fun setListener() {


        FirebaseAuth.getInstance().currentUser?.let {
            viewModel.getProductByID(it.uid)
        }

    }

    override fun allObserver() {
        viewModel.productResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error->{
                    loading.dismiss()

                }
                is DataState.Loading ->{
                    loading.show()

                }
                is DataState.Success ->{
                    it.data?.let { it1 -> setDataToRV(it1) }
                    loading.dismiss()



                }
            }
        }

    }

    private fun setDataToRV(it: List<Product>) {
        binding.rvSeller.adapter = SellerProductAdapter(it)

    }


}