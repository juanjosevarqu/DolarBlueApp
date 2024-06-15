package com.varqulabs.dolarblue.history.di

import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionHistoryDao
import com.varqulabs.dolarblue.history.data.repository.ConversionsHistoryRepositoryImpl
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import com.varqulabs.dolarblue.history.domain.usecases.GetConversionsHistoryUseCase
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
    fun provideConversionsHistoryRepository(conversionHistoryDao: ConversionHistoryDao) : ConversionsHistoryRepository {
        return ConversionsHistoryRepositoryImpl(conversionHistoryDao)
    }

    @Provides
    @Singleton
    fun provideGetConversionUseCase(repository: ConversionsHistoryRepository): GetConversionsHistoryUseCase {
        return GetConversionsHistoryUseCase(repository)
    }
}