package com.varqulabs.dolarblue.history.domain.usecases

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetConversionsHistoryUseCase(
    private val conversionsHistoryRepository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(): Flow<List<ConversionsHistory>> {
        return conversionsHistoryRepository.getConversionsHistory()
    }
}