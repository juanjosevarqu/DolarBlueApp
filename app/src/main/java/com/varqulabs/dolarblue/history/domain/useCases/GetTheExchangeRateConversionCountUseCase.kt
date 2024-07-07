package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTheExchangeRateConversionCountUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionsHistoryRepository: ConversionsHistoryRepository
): UseCase<Int, Int>(dispatcher) {
    override suspend fun executeData(input: Int): Flow<Int> {
        return flow {
            conversionsHistoryRepository.getTheExchangeRateConversionCount(input)
        }
    }
}