package com.creativeitinstitute.bazar.data.repository

import android.net.Uri
import com.creativeitinstitute.bazar.core.Nodes
import com.creativeitinstitute.bazar.data.Product
import com.creativeitinstitute.bazar.data.models.AuthSource
import com.creativeitinstitute.bazar.data.models.SellerSource
import com.creativeitinstitute.bazar.views.login.UserLogin
import com.creativeitinstitute.bazar.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class SellerRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
    ): SellerSource {
    override fun uploadProductImage(productImageUri: Uri) : UploadTask{

        val storage: StorageReference = storageRef.child("products").child("PRD_${System.currentTimeMillis()}")

       return storage.putFile(productImageUri)

    }

    override fun uploadProduct(product: Product):Task<Void> {

      return db.collection(Nodes.PRODUCT).document(product.productID).set(product)

    }

    override fun getAllProductByUserID(userID: String) :Task<QuerySnapshot> {
      return  db.collection(Nodes.PRODUCT).whereEqualTo("sellerID", userID).get()
    }

}