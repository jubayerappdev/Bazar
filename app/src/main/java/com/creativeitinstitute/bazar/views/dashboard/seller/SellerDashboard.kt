package com.creativeitinstitute.bazar.views.dashboard.seller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.creativeitinstitute.bazar.R
import com.creativeitinstitute.bazar.databinding.ActivitySellerDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerDashboard : AppCompatActivity() {
    lateinit var binding : ActivitySellerDashboardBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySellerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.myProductFragment,
                R.id.uploadProductFragment,
                R.id.sellerProfileFragment,

                )
        )
        binding.bottomNavigation.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)





    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}