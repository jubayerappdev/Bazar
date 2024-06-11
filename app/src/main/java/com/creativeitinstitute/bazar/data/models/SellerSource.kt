package com.creativeitinstitute.bazar.data.models

import android.net.Uri
import com.creativeitinstitute.bazar.data.Product
import com.creativeitinstitute.bazar.views.login.UserLogin
import com.creativeitinstitute.bazar.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.UploadTask

interface SellerSource {

    fun uploadProductImage(productImageUri: Uri):UploadTask
    fun uploadProduct(product: Product):Task<Void>

    fun getAllProductByUserID(userID:String) :Task<QuerySnapshot>


}