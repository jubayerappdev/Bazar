package com.creativeitinstitute.bazar.di

import com.creativeitinstitute.bazar.data.repository.AuthRepository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun providesFirebaseFireStoreDB(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Provides
    @Singleton
    fun providesFirebase(jAuth: FirebaseAuth, db :FirebaseFirestore): AuthRepository {
        return AuthRepository(jAuth, db)
    }


    @Provides
    @Singleton
    fun providesFirebaseStorage(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }



}