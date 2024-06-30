package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ResetPasswordUseCase(
    dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
): UseCase<String, Boolean>(dispatcher){
    override suspend fun executeData(input: String): Flow<Boolean> {
       return authRepository.resetPassword(input)
    }
}