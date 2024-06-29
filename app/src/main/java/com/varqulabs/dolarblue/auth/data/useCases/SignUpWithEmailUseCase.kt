package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class SignUpWithEmailUseCase(
    dispatcher: CoroutineDispatcher,
    private val userAuthRepository: AuthRepository
) : UseCase<AuthRequest, Boolean>(dispatcher) {
    override suspend fun executeData(input: AuthRequest): Flow<Boolean> {
        return userAuthRepository.signUpWithEmailAndPassword(input)
    }
}