package com.creativeitinstitute.bazar.views.starter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.bazar.R
import com.creativeitinstitute.bazar.base.BaseFragment
import com.creativeitinstitute.bazar.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {


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
        FirebaseAuth.getInstance().currentUser?.let {
            findNavController().navigate(R.id.action_startFragment_to_SellerDashboardFragment)
        }
    }

    override fun allObserver() {

    }

}