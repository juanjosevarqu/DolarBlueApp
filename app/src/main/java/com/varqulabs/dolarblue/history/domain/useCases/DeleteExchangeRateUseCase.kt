package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteExchangeRateUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionsHistoryRepository: ConversionsHistoryRepository
): UseCase<Int, Unit>(dispatcher) {
    override suspend fun executeData(input: Int): Flow<Unit> {
        return flow {
            conversionsHistoryRepository.deleteExchangeRate(input)
            emit(Unit)
        }
    }
}