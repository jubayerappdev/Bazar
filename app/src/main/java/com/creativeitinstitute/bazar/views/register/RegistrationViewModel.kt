package com.creativeitinstitute.bazar.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.data.models.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {

    private val _registrationResponse = MutableLiveData<DataState<User>>()
    val registrationResponse: LiveData<DataState<User>> = _registrationResponse



    fun userRegistration(user: User){

        _registrationResponse.postValue(DataState.Loading())

        authService.userRegistration(user).addOnSuccessListener {
            _registrationResponse.postValue(DataState.Success(user))
            Log.d("TAG", "userRegistration: Success")
        }.addOnFailureListener {error->

            _registrationResponse.postValue(DataState.Error("${error.message}"))
            Log.d("TAG", "userRegistration: ${error.message}")
        }
    }


}