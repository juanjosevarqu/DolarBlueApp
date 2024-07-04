package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetConversionsHistoryFlowUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionsHistoryRepository: ConversionsHistoryRepository
): UseCase<Unit, List<ConversionsHistory>>(dispatcher) {
    override suspend fun executeData(input: Unit): Flow<List<ConversionsHistory>> {
        return conversionsHistoryRepository.getConversionsHistoryFlow()
    }
}