package com.creativeitinstitute.bazar.views.dashboard

import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.bazar.R
import com.creativeitinstitute.bazar.base.BaseFragment
import com.creativeitinstitute.bazar.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerDashboardFragment : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    @Inject
    lateinit var jAuth:FirebaseAuth


    override fun setListener() {

        binding.apply {
            btnLogout.setOnClickListener{
                jAuth.signOut()

                findNavController().navigate(R.id.action_SellerDashboardFragment_to_startFragment)

            }
        }

    }

    override fun allObserver() {

    }


}