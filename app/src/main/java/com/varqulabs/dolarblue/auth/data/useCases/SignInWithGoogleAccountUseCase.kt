package com.varqulabs.dolarblue.auth.data.useCases

import com.google.firebase.auth.AuthCredential
import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow


class SignInWithGoogleAccountUseCase(
    dispatcher: CoroutineDispatcher,
    private val repository: AuthRepository
) : UseCase<AuthCredential, Boolean>(dispatcher) {
    override suspend fun executeData(input: AuthCredential): Flow<Boolean> {
        return repository.signInWithGoogleAccount(input)
    }
}