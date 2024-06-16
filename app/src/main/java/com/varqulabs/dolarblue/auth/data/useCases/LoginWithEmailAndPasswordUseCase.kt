package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.auth.domain.model.AuthResult
import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LoginWithEmailAndPasswordUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : UseCase<LoginRequest, AuthResult<Unit>>(dispatcher) {
    override fun executeData(input: LoginRequest): Flow<AuthResult<Unit>> {
        return authRepository.login(loginRequest = input).flowOn(dispatcher)
    }
}