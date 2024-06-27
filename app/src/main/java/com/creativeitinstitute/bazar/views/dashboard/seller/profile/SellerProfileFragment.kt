package com.creativeitinstitute.bazar.views.dashboard.seller.profile

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import com.creativeitinstitute.bazar.R
import com.creativeitinstitute.bazar.base.BaseFragment
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.core.areAllPermissionGranted
import com.creativeitinstitute.bazar.core.extract
import com.creativeitinstitute.bazar.core.requestPermissions
import com.creativeitinstitute.bazar.databinding.FragmentSellerProfileBinding
import com.creativeitinstitute.bazar.views.dashboard.seller.upload.UploadProductFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerProfileFragment : BaseFragment<FragmentSellerProfileBinding>(FragmentSellerProfileBinding::inflate) {

    private var sellerProfile:SellerProfile? = null
    private val viewModel:SellerProfileViewModel by viewModels()

    private var hashLocalImageUrl : Boolean = false
    override fun setListener() {

        FirebaseAuth.getInstance().currentUser?.let {
            viewModel.getUserByUserID(it.uid)
        }
        permissionRequest = getPermissionRequest()
        binding.apply {
            ivProfile.setOnClickListener {
                requestPermissions(permissionRequest, permissionList)
            }
            btnUpdate.setOnClickListener {
                loading.show()

                val name = etName.extract()
                val email = etEmail.extract()

                sellerProfile.apply {
                    this?.name = name
                    this?.email = email
                }

                updateProfile(sellerProfile)


            }
        }

    }

    private fun updateProfile(sellerProfile: SellerProfile?) {
        sellerProfile?.let { viewModel.updateProfile(it, hashLocalImageUrl) }
    }

    override fun allObserver() {
        viewModel.profileUpdateResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error->{
                    loading.dismiss()

                }
                is DataState.Loading ->{
                    loading.show()

                }
                is DataState.Success ->{

                    Toast.makeText(requireContext(),it.data, Toast.LENGTH_LONG).show()
                    loading.dismiss()



                }
            }
        }
        viewModel.logedInUserResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error->{
                    loading.dismiss()

                }
                is DataState.Loading ->{
                    loading.show()

                }
                is DataState.Success ->{
                    sellerProfile = it.data
                    sellerProfileData(sellerProfile)

                    loading.dismiss()



                }
            }
        }

    }

    private fun sellerProfileData(sellerProfile: SellerProfile?) {
        hashLocalImageUrl = sellerProfile?.userImage.isNullOrBlank()

        binding.apply {
            etName.setText(sellerProfile?.name)
            etEmail.setText(sellerProfile?.email)
            ivProfile.load(sellerProfile?.userImage)
        }

    }

    companion object{
        private val permissionList = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }
    private lateinit var permissionRequest: ActivityResultLauncher<Array<String>>

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                Log.d("TAG", "$fileUri ")

                binding.ivProfile.setImageURI(fileUri)
                sellerProfile?.userImage = fileUri.toString()
                hashLocalImageUrl = true




            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    private fun getPermissionRequest(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

            if (areAllPermissionGranted(permissionList)){
                //ase

                Toast.makeText(requireContext(),"Ase", Toast.LENGTH_LONG).show()

                ImagePicker.with(this)
                    .compress(1024)         //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(512, 512)  //Final image resolution will be less than 1080 x 1080(Optional)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }
            }else{
                //nai
                Toast.makeText(requireContext(),"Nai", Toast.LENGTH_LONG).show()
            }
        }
    }

}