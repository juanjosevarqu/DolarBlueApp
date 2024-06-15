package com.varqulabs.dolarblue.history.domain.usecases

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository

class GetConversionsHistoryUseCase(
    private val conversionsHistoryRepository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(): List<ConversionsHistory> {
        return conversionsHistoryRepository.getConversionsHistory()
    }
}