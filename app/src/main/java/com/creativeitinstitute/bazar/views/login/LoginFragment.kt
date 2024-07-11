package com.creativeitinstitute.bazar.views.login

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
import com.creativeitinstitute.bazar.databinding.FragmentLoginBinding
import com.creativeitinstitute.bazar.isEmpty
import com.creativeitinstitute.bazar.views.dashboard.customer.CustomerDashboard
import com.creativeitinstitute.bazar.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel:LoginViewModel by viewModels()

    override fun setListener() {

        with(binding){
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()

                if (!etEmail.isEmpty() && !etPassword.isEmpty()){
                    Toast.makeText(context, "All input done !", Toast.LENGTH_LONG).show()

                    val user = UserLogin(etEmail.text.toString(), etPassword.text.toString())
                    viewModel.userLogin(user)
                    loading.show()
                }
            }
        }

    }

    override fun allObserver() {

        viewModel.loginResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error->{
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
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