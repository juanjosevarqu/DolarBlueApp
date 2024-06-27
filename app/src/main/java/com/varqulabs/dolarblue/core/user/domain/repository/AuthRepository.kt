package com.varqulabs.dolarblue.core.user.domain.repository

import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequest: LoginRequest): Flow<Boolean>

    fun resetPassword(email: String): Flow<Boolean>

}