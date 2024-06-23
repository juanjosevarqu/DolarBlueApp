package com.varqulabs.dolarblue.core.user.domain.repository

import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequest: LoginRequest): Flow<Result<User>>
    fun resetPassword(email: String): Flow<Result<Boolean>>
}