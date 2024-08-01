package com.creativeitinstitute.bazar.views.starter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.bazar.R
import com.creativeitinstitute.bazar.base.BaseFragment
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.core.Nodes
import com.creativeitinstitute.bazar.databinding.FragmentStartBinding
import com.creativeitinstitute.bazar.views.dashboard.customer.CustomerDashboard
import com.creativeitinstitute.bazar.views.dashboard.seller.SellerDashboard
import com.creativeitinstitute.bazar.views.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    private val  viewModel: LoginViewModel by viewModels()


    override fun setListener() {

        setUpAutoLogin()


            with(binding) {
                btnLogin.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_loginFragment)
                }
                btnRegister.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_registerFragment)
                }
            }


    }

    private fun setUpAutoLogin() {
        val user:FirebaseUser?=FirebaseAuth.getInstance().currentUser

        if (user != null){
            binding.apply {
                layoutLoading.visibility = View.VISIBLE
                layoutMain.visibility = View.GONE
            }
            user.uid.let {data ->

                viewModel.checkUserByID(data)

            }
        }else{
            binding.apply {
                layoutLoading.visibility = View.GONE
                layoutMain.visibility = View.VISIBLE
            }

        }



    }

    override fun allObserver() {

        viewModel.loginResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error->{
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding.apply {
                        layoutLoading.visibility = View.GONE
                        layoutMain.visibility = View.VISIBLE
                    }
                }
                is DataState.Loading ->{
                    loading.show()
                    Toast.makeText(context, "Loading.....", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success ->{
                    loading.dismiss()

                    it.data?.apply {
                        when(userType){
                            Nodes.USER_TYPE_CUSTOMER ->{
                                startActivity(Intent(requireContext(), CustomerDashboard::class.java))
                                requireActivity().finish()
                            }
                            Nodes.USER_TYPE_SELLER ->{
                                startActivity(Intent(requireContext(), SellerDashboard::class.java))
                                requireActivity().finish()
                            }
                            else ->{
                                Toast.makeText(requireContext(), "Something is wrong!", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }

                }
            }
        }
    }

}