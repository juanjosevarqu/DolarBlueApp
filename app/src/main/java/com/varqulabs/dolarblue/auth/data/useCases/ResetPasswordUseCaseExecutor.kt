package com.varqulabs.dolarblue.auth.data.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCaseExecutor
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


class ResetPasswordUseCaseExecutor(
    private val dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
): UseCaseExecutor<String, Result<Boolean>>(dispatcher){
    override fun executeData(input: String): Flow<Result<Boolean>> {
        return authRepository.resetPassword(input).flowOn(dispatcher)
    }

}