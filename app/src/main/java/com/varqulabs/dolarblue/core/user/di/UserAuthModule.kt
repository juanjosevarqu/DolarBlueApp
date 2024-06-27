package com.varqulabs.dolarblue.core.user.di

import com.google.firebase.auth.FirebaseAuth
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.user.data.repository.AuthRepositoryImpl
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserAuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, preferencesRepository: PreferencesRepository): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, preferencesRepository)
    }

}