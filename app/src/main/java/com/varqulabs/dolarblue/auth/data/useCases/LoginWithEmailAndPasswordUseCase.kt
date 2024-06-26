package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class LoginWithEmailAndPasswordUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : UseCase<LoginRequest, Boolean>(dispatcher) {
    override suspend fun executeData(input: LoginRequest): Flow<Boolean> {
        return authRepository.login(loginRequest = input)
    }
}