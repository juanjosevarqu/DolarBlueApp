package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
import com.varqulabs.dolarblue.calculator.data.repository.DolarBlueRepositoryImpl
import com.varqulabs.dolarblue.calculator.domain.repository.DolarBlueRepository
import com.varqulabs.dolarblue.calculator.domain.usecases.GetDolarBlueUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CalculatorModule {

    @Provides
    @Singleton
    fun provideDolarBlueService(): DolarBlueService = Retrofit.Builder()
        .baseUrl("https://api.bluelytics.com.ar/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(DolarBlueService::class.java)

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