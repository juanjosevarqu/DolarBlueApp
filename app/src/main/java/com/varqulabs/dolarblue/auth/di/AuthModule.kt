package com.varqulabs.dolarblue.auth.di

import com.varqulabs.dolarblue.auth.data.useCases.ResetPasswordUseCase
import com.varqulabs.dolarblue.auth.data.useCases.SignUpWithEmailUseCase
import com.varqulabs.dolarblue.core.di.IoDispatcher
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideResetPasswordUse(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): ResetPasswordUseCase {
        return ResetPasswordUseCase(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideLoginWithEmailAndPasswordUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): ResetPasswordUseCase {
        return ResetPasswordUseCase(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideSignUpWithEmailUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): SignUpWithEmailUseCase {
        return SignUpWithEmailUseCase(
            dispatcher = dispatcher,
            userAuthRepository = authRepository
        )
    }
}
