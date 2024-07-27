package com.varqulabs.dolarblue.auth.di

import com.varqulabs.dolarblue.auth.data.useCases.LoginWithEmailAndPasswordUseCase
import com.varqulabs.dolarblue.auth.data.useCases.LogoutUserUseCase
import com.varqulabs.dolarblue.auth.data.useCases.ResetPasswordUseCase
import com.varqulabs.dolarblue.auth.data.useCases.SendEmailVerifiedUseCase
import com.varqulabs.dolarblue.auth.data.useCases.SignInWithGoogleAccountUseCase
import com.varqulabs.dolarblue.auth.data.useCases.SignUpWithEmailUseCase
import com.varqulabs.dolarblue.auth.data.useCases.VerifiedAccountUseCase
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
    fun provideSendEmailVerifiedUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): SendEmailVerifiedUseCase {
        return SendEmailVerifiedUseCase(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideVerifiedAccountUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): VerifiedAccountUseCase {
        return VerifiedAccountUseCase(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideLoginWithEmailAndPassword(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): LoginWithEmailAndPasswordUseCase{
        return LoginWithEmailAndPasswordUseCase(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideLogoutUserUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): LogoutUserUseCase {
        return LogoutUserUseCase(
            dispatcher = dispatcher,
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideResetPasswordUseCase(
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

    @Provides
    @Singleton
    fun provideSignUpWithGoogleAccountUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        authRepository: AuthRepository
    ): SignInWithGoogleAccountUseCase {
        return SignInWithGoogleAccountUseCase(
            dispatcher = dispatcher,
            repository = authRepository
        )
    }
}