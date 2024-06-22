package com.varqulabs.dolarblue.core.user.domain.repository

import com.google.firebase.auth.AuthResult
import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import com.varqulabs.dolarblue.core.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequest: AuthRequest): Flow<Result<User>>

    fun resetPassword(email: String): Flow<Boolean>

    fun signUpWithEmailAndPassword(signupRequest: AuthRequest): Flow<AuthResult>

}