package com.varqulabs.dolarblue.auth.data.useCases

import com.google.firebase.auth.AuthResult
import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import com.varqulabs.dolarblue.core.user.domain.repository.UserAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class SignUpWithEmailUseCase(
    dispatcher: CoroutineDispatcher,
    private val userAuthRepository: AuthRepository
) : UseCase<AuthRequest, AuthResult>(dispatcher){
    override fun executeData(input: AuthRequest): Flow<AuthResult> {
        return userAuthRepository.signUpWithEmailAndPassword(input)
    }
}