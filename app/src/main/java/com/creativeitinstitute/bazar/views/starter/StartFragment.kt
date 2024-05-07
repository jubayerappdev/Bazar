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


class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {


    override fun setListener() {


            with(binding) {
                btnLogin.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_loginFragment)
                }
                btnRegister.setOnClickListener {
                    findNavController().navigate(R.id.action_startFragment_to_registerFragment)
                }
            }


    }

    override fun allObserver() {
        TODO("Not yet implemented")
    }

}