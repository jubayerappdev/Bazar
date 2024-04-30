package com.creativeitinstitute.bazar.views.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.bazar.data.models.AuthService

class RegistrationViewModel : ViewModel() {



    fun userRegistration(user: User){
        val authService = AuthService()

        authService.userRegistration(user).addOnSuccessListener {
            Log.d("TAG", "userRegistration: Success")
        }.addOnFailureListener {error->
            Log.d("TAG", "userRegistration: ${error.message}")
        }
    }


}