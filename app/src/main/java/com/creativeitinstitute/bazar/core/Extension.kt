package com.creativeitinstitute.bazar.core

import android.content.pm.PackageManager
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun EditText.extract():String{
    return text.toString().trim()
}


fun Fragment.requestPermissions(
    request : ActivityResultLauncher<Array<String>>,
    permissions: Array<String>
){
   request.launch(permissions)
}

fun Fragment.areAllPermissionGranted(permissions: Array<String>):Boolean{

    return permissions.all {
        ContextCompat.checkSelfPermission(requireContext(),it) == PackageManager.PERMISSION_GRANTED
    }
}



