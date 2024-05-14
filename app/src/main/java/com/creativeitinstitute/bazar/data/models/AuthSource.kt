package com.creativeitinstitute.bazar.data.models

import com.creativeitinstitute.bazar.views.login.UserLogin
import com.creativeitinstitute.bazar.views.register.UserRegister
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthSource {

    fun userRegistration(user: UserRegister) : Task<AuthResult>
    fun userLogin(user: UserLogin) : Task<AuthResult>

    fun createUser(user: UserRegister) : Task<Void>
    fun userForgetPassword(email: String)
}