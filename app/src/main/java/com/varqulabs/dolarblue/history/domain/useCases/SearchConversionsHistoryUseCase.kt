package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.history.domain.model.ConversionSearch
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class SearchConversionsHistoryUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionsHistoryRepository: ConversionsHistoryRepository
): UseCase<ConversionSearch, List<ConversionsHistory>>(dispatcher) {
    override suspend fun executeData(input: ConversionSearch): Flow<List<ConversionsHistory>> {
        return conversionsHistoryRepository.searchConversionsHistoryByQueryAndCurrency(input)
    }
}