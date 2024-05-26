package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
import com.varqulabs.dolarblue.calculator.data.repository.DolarBlueRepositoryImpl
import com.varqulabs.dolarblue.calculator.domain.repository.DollarBlueRepository
import com.varqulabs.dolarblue.calculator.domain.usecases.GetDollarBlueUseCase
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
    fun provideDolarBlueRepository(service: DolarBlueService): DollarBlueRepository {
        return DolarBlueRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetDolarBlueUseCase(repository: DollarBlueRepository): GetDollarBlueUseCase {
        return GetDollarBlueUseCase(repository)
    }
}