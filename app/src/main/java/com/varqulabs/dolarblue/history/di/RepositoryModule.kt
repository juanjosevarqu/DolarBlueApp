package com.varqulabs.dolarblue.history.di

import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.history.data.repository.ConversionsHistoryRepositoryImpl
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import com.varqulabs.dolarblue.history.domain.useCases.UpdateConversionUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetConversionsHistoryFlowUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetFavoriteConversionsHistoryUseCase
import com.varqulabs.dolarblue.history.domain.useCases.SearchConversionsHistoryUseCase
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
    fun provideConversionsHistoryRepository(conversionsHistoryDao: ConversionsHistoryDao) : ConversionsHistoryRepository {
        return ConversionsHistoryRepositoryImpl(conversionsHistoryDao)
    }

    @Provides
    @Singleton
    fun provideGetConversionsHistoryFlowUseCase(repository: ConversionsHistoryRepository): GetConversionsHistoryFlowUseCase {
        return GetConversionsHistoryFlowUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateConversionUseCase(repository: ConversionsHistoryRepository): UpdateConversionUseCase {
        return UpdateConversionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteConversionsHistoryUseCase(repository: ConversionsHistoryRepository): GetFavoriteConversionsHistoryUseCase {
        return GetFavoriteConversionsHistoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSearchConversionsHistoryUseCase(repository: ConversionsHistoryRepository): SearchConversionsHistoryUseCase {
        return SearchConversionsHistoryUseCase(repository)
    }
}