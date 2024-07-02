package com.creativeitinstitute.bazar.views.dashboard.seller.profile

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.bazar.core.DataState
import com.creativeitinstitute.bazar.data.Product
import com.creativeitinstitute.bazar.data.repository.AuthRepository
import com.creativeitinstitute.bazar.data.repository.SellerProfileRepository
import com.creativeitinstitute.bazar.data.repository.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SellerProfileViewModel @Inject constructor(private val repo: SellerProfileRepository) : ViewModel() {

    private val _profileUpdateResponse = MutableLiveData<DataState<String>>()
    val profileUpdateResponse: LiveData<DataState<String>> = _profileUpdateResponse

    fun updateProfile(user: SellerProfile, hasLocalImageUrl: Boolean) {
        _profileUpdateResponse.postValue(DataState.Loading())

        if (hasLocalImageUrl) {
            val imageUri: Uri? = user.userImage?.toUri()
            imageUri?.let {
                repo.uploadImage(it).addOnSuccessListener { snapshot ->
                    snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url ->
                        user.userImage = url.toString()
                        repo.updateUser(user).addOnSuccessListener {
                            _profileUpdateResponse.postValue(DataState.Success("Uploaded and Updated user Profile Successfully!"))
                        }.addOnFailureListener {
                            _profileUpdateResponse.postValue(DataState.Error(it.message ?: "An unknown error occurred"))
                        }
                    }
                }.addOnFailureListener {
                    _profileUpdateResponse.postValue(DataState.Error("Image Upload failed..."))
                }
            }
        } else {
            repo.updateUser(user).addOnSuccessListener {
                _profileUpdateResponse.postValue(DataState.Success("Uploaded and Updated user Profile Successfully!"))
            }.addOnFailureListener {
                _profileUpdateResponse.postValue(DataState.Error(it.message ?: "An unknown error occurred"))
            }
        }
    }

    private val _loggedInUserResponse = MutableLiveData<DataState<SellerProfile>>()
    val loggedInUserResponse: LiveData<DataState<SellerProfile>> get() = _loggedInUserResponse

    fun getUserByUserID(userID: String) {
        _loggedInUserResponse.postValue(DataState.Loading())

        repo.getUserByUserID(userID).addOnSuccessListener { value ->
            val documents = value.documents
            if (documents.isNotEmpty()) {
                _loggedInUserResponse.postValue(
                    DataState.Success(
                        documents[0].toObject(SellerProfile::class.java)
                    )
                )
            } else {
                _loggedInUserResponse.postValue(DataState.Error("No user found with this ID"))
            }
        }.addOnFailureListener {
            _loggedInUserResponse.postValue(DataState.Error(it.message ?: "An unknown error occurred"))
        }
    }
}
