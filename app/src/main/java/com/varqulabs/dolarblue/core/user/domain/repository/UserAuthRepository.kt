package com.varqulabs.dolarblue.core.user.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserAuthRepository<T> {
    fun signUpWithEmail(email: String, password: String): Flow<T>
}