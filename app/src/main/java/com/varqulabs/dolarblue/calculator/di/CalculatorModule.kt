package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.remote.DollarBlueService
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
    fun provideDollarBlueService(): DollarBlueService = Retrofit.Builder()
        .baseUrl("https://api.bluelytics.com.ar/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(DollarBlueService::class.java)

}