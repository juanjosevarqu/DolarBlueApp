package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.model.QueryAndCurrency
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow

class SearchConversionsHistoryUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(queryAndCurrency: QueryAndCurrency): Flow<List<ConversionsHistory>> {
        return repository.searchConversionsHistoryByQueryAndCurrency(queryAndCurrency)
    }
}