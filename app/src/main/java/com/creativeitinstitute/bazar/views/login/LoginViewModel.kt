package com.creativeitinstitute.bazar.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: AuthRepository):ViewModel(){

    private val _loginResponse = MutableLiveData<DataState<UserLogin>>()
    val loginResponse : LiveData<DataState<UserLogin>> = _loginResponse


    fun userLogin(user:UserLogin){

        _loginResponse.postValue(DataState.Loading())
        authService.userLogin(user).addOnSuccessListener {

            _loginResponse.postValue(DataState.Success(user))
            Log.d("TAG", "login: Success ")
        }.addOnFailureListener {error->

            _loginResponse.postValue(DataState.Error("${error.message}"))
            Log.d("TAG", "userLogin: ${error.message}")
        }

    }



}