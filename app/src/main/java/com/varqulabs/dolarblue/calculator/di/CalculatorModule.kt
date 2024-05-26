package com.varqulabs.dolarblue.calculator.di

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
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

}