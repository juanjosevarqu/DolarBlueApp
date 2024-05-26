package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
import com.varqulabs.dolarblue.calculator.data.repository.DolarBlueRepositoryImpl
import com.varqulabs.dolarblue.calculator.domain.repository.DolarBlueRepository
import com.varqulabs.dolarblue.calculator.domain.usecases.GetDolarBlueUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDolarBlueRepository(service: DolarBlueService): DolarBlueRepository {
        return DolarBlueRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetDolarBlueUseCase(repository: DolarBlueRepository): GetDolarBlueUseCase {
        return GetDolarBlueUseCase(repository)
    }
}