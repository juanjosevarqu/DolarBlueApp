package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.domain.useCases.UseCaseExecutor
import com.varqulabs.dolarblue.core.user.domain.model.User
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LoginWithEmailAndPasswordUseCaseExecutor(
    private val dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : UseCaseExecutor<LoginRequest, Result<User>>(dispatcher) {
    override fun executeData(input: LoginRequest): Flow<Result<User>> {
        return authRepository.login(loginRequest = input).flowOn(dispatcher)
    }
}