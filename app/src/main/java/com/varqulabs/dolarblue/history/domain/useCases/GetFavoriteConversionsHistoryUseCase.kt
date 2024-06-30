package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteConversionsHistoryUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(): Flow<List<ConversionsHistory>> {
        return repository.getFavoriteConversionsHistory()
    }
}