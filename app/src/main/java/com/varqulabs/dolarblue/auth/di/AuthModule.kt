package com.varqulabs.dolarblue.auth.di

import com.varqulabs.dolarblue.auth.data.useCases.ResetPasswordUseCaseExecutor
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
    ): ResetPasswordUseCaseExecutor {
        return ResetPasswordUseCaseExecutor(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideLoginWithEmailAndPasswordUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): ResetPasswordUseCaseExecutor {
        return ResetPasswordUseCaseExecutor(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }
}
