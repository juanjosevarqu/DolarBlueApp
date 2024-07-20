package com.varqulabs.dolarblue.history.di

import com.varqulabs.dolarblue.calculator.data.local.database.dao.ConversionDao
import com.varqulabs.dolarblue.core.di.IoDispatcher
import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.history.data.repository.ConversionsHistoryRepositoryImpl
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import com.varqulabs.dolarblue.history.domain.useCases.UpdateConversionUseCase
import com.varqulabs.dolarblue.history.domain.useCases.DeleteConversionUseCase
import com.varqulabs.dolarblue.history.domain.useCases.DeleteExchangeRateUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetConversionsHistoryFlowUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetFavoriteConversionsHistoryUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetExchangeRateConversionCountUseCase
import com.varqulabs.dolarblue.history.domain.useCases.InsertConversionUseCase
import com.varqulabs.dolarblue.history.domain.useCases.SearchConversionsHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideConversionsHistoryRepository(
        conversionsHistoryDao: ConversionsHistoryDao,
        conversionDao: ConversionDao
    ) : ConversionsHistoryRepository {
        return ConversionsHistoryRepositoryImpl(
            conversionHistoryDao = conversionsHistoryDao,
            conversionDao = conversionDao
        )
    }

    @Provides
    @Singleton
    fun provideGetConversionsHistoryFlowUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): GetConversionsHistoryFlowUseCase {
        return GetConversionsHistoryFlowUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateConversionUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): UpdateConversionUseCase {
        return UpdateConversionUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetFavoriteConversionsHistoryUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): GetFavoriteConversionsHistoryUseCase {
        return GetFavoriteConversionsHistoryUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideDeleteConversionUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): DeleteConversionUseCase {
        return DeleteConversionUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideSearchConversionsHistoryUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): SearchConversionsHistoryUseCase {
        return SearchConversionsHistoryUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideDeleteExchangeRateUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): DeleteExchangeRateUseCase {
        return DeleteExchangeRateUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetExchangeRateConversionCountUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): GetExchangeRateConversionCountUseCase {
        return GetExchangeRateConversionCountUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }

    @Provides
    @Singleton
    fun provideInsertConversionUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        conversionsHistoryRepository: ConversionsHistoryRepository
    ): InsertConversionUseCase {
        return InsertConversionUseCase(
            dispatcher = dispatcher,
            conversionsHistoryRepository = conversionsHistoryRepository
        )
    }
}