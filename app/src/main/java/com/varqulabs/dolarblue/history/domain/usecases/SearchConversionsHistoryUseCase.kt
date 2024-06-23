package com.varqulabs.dolarblue.history.domain.usecases

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow

class SearchConversionsHistoryUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(searchQuery: String): Flow<List<ConversionsHistory>> {
        return repository.search2(searchQuery)
    }
}