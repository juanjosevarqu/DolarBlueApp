package com.varqulabs.dolarblue.core.user.domain.repository

import com.google.firebase.auth.AuthCredential
import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun logout(): Unit

    fun login(loginRequest: AuthRequest): Flow<Boolean>

    fun signInWithGoogleAccount(credential: AuthCredential): Flow<Boolean>

    fun signUpWithEmailAndPassword(signupRequest: AuthRequest): Flow<Boolean>

    fun sendEmailVerified(): Flow<Unit>

    fun resetPassword(email: String): Flow<Boolean>

    val verifiedAccount: Flow<Boolean>

}