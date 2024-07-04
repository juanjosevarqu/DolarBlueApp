package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.model.ConversionSearch
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow

class SearchConversionsHistoryUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(conversionSearch: ConversionSearch): Flow<List<ConversionsHistory>> {
        return repository.searchConversionsHistoryByQueryAndCurrency(conversionSearch)
    }
}