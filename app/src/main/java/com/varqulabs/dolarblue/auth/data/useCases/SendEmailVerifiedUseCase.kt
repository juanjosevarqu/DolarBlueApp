package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class SendEmailVerifiedUseCase(
    dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) : UseCase<Unit, Unit>(dispatcher) {
    override suspend fun executeData(input: Unit): Flow<Unit> {
        return authRepository.sendEmailVerified()
    }
}