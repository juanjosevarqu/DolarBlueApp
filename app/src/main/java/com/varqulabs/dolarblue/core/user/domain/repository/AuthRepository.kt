package com.varqulabs.dolarblue.core.user.domain.repository

import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequest: AuthRequest): Flow<Boolean>

    fun resetPassword(email: String): Flow<Boolean>

    fun signUpWithEmailAndPassword(signupRequest: AuthRequest): Flow<Boolean>

}