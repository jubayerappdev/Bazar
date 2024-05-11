package com.creativeitinstitute.bazar.data.models

import com.creativeitinstitute.bazar.views.register.User
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthService @Inject constructor(private val jAuth:FirebaseAuth): AuthSource {
    override fun userRegistration(user: User) :Task<AuthResult> {


        return jAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: User) {

        jAuth.signInWithEmailAndPassword(user.email, user.password)
    }

    override fun userForgetPassword(email: String) {

    }
}