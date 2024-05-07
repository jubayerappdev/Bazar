package com.creativeitinstitute.bazar.data.models

import com.creativeitinstitute.bazar.views.register.User
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthService : AuthSource {
    override fun userRegistration(user: User) :Task<AuthResult> {
        val jAuth = FirebaseAuth.getInstance()

        return jAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun userLogin(user: User) {
        val jAuth = FirebaseAuth.getInstance()
        jAuth.signInWithEmailAndPassword(user.email, user.password)
    }

    override fun userForgetPassword(email: String) {
        TODO("Not yet implemented")
    }
}