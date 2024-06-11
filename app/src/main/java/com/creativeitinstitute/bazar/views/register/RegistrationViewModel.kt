package com.creativeitinstitute.bazar.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authService: AuthRepository) : ViewModel() {

    private val _registrationResponse = MutableLiveData<DataState<UserRegister>>()
    val registrationResponse: LiveData<DataState<UserRegister>> = _registrationResponse



    fun userRegistration(user: UserRegister){

        _registrationResponse.postValue(DataState.Loading())


        authService.userRegistration(user)
            .addOnSuccessListener {

                it.user?.let {createUser->

                    user.userID = createUser.uid

                    authService.createUser(user).addOnSuccessListener {



                        _registrationResponse.postValue(DataState.Success(user))
                        Log.d("TAG", "userRegistration: Success")


                    }.addOnFailureListener {error->
                        _registrationResponse.postValue(DataState.Error("${error.message}"))
                        Log.d("TAG", "userRegistration: ${error.message}")
                    }
                }





        }.addOnFailureListener {error->

            _registrationResponse.postValue(DataState.Error("${error.message}"))
            Log.d("TAG", "userRegistration: ${error.message}")
        }
    }


}