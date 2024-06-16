package com.varqulabs.dolarblue.core.user.domain.repository

import com.varqulabs.dolarblue.auth.domain.model.AuthResult
import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequest: LoginRequest): Flow<AuthResult<Unit>>

}