package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.remote.DollarBlueService
import com.varqulabs.dolarblue.calculator.data.repository.DollarBlueRepositoryImpl
import com.varqulabs.dolarblue.calculator.domain.repository.DollarBlueRepository
import com.varqulabs.dolarblue.calculator.domain.useCases.GetDollarBlueUseCase
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
    fun provideDollarBlueRepository(service: DollarBlueService): DollarBlueRepository {
        return DollarBlueRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetDollarBlueUseCase(repository: DollarBlueRepository): GetDollarBlueUseCase {
        return GetDollarBlueUseCase(repository)
    }
}