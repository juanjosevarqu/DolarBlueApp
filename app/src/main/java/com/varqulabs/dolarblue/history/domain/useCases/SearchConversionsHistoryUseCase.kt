package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.history.domain.model.QueryAndCurrency
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class SearchConversionsHistoryUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionsHistoryRepository: ConversionsHistoryRepository
): UseCase<QueryAndCurrency, List<ConversionsHistory>>(dispatcher) {
    override suspend fun executeData(input: QueryAndCurrency): Flow<List<ConversionsHistory>> {
        return conversionsHistoryRepository.searchConversionsHistoryByQueryAndCurrency(input)
    }
}