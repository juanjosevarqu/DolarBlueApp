package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.core.domain.useCases.UseCase
import com.varqulabs.dolarblue.history.domain.model.Conversion
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateConversionUseCase(
    dispatcher: CoroutineDispatcher,
    private val conversionsHistoryRepository: ConversionsHistoryRepository
): UseCase<Conversion, Unit>(dispatcher) {
    override suspend fun executeData(input: Conversion): Flow<Unit> {
        return flow {
            conversionsHistoryRepository.updateConversion(input)
            emit(Unit)
        }
    }
}