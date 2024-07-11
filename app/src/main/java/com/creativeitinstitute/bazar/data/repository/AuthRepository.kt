package com.creativeitinstitute.bazar.data.repository

import com.creativeitinstitute.bazar.core.Nodes
import com.creativeitinstitute.bazar.data.models.AuthSource
import com.creativeitinstitute.bazar.views.login.UserLogin
import com.creativeitinstitute.bazar.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val jAuth:FirebaseAuth,
    private val db: FirebaseFirestore
    ): AuthSource {
    override fun userRegistration(user: UserRegister) :Task<AuthResult> {


        return jAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: UserLogin) : Task<AuthResult>{

       return jAuth.signInWithEmailAndPassword(user.email, user.password)
    }

    override fun createUser(user: UserRegister) : Task<Void>{
      return  db.collection(Nodes.USER).document(user.userID).set(user)

    }

    override fun userForgetPassword(email: String) {

    }

    fun getUserByUserID(userID: String) :Task<QuerySnapshot> {
        return  db.collection(Nodes.USER).whereEqualTo("userID", userID).get()
    }
}