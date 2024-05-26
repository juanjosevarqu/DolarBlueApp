package com.varqulabs.dolarblue.calculator.domain.usecases

import com.varqulabs.dolarblue.calculator.domain.model.DollarBlue
import com.varqulabs.dolarblue.calculator.domain.repository.DollarBlueRepository
import com.varqulabs.dolarblue.core.domain.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDollarBlueUseCase(
    private val blueRepository: DollarBlueRepository,
) {

    suspend operator fun invoke(): Flow<DataState<DollarBlue>> {
        return flow {
            try {
                emit(DataState.Loading)
                val dolarBlue = blueRepository.getDollarBlue()
                emit(DataState.Success(dolarBlue))
            } catch (e: Exception) {
                emit(
                    DataState.Error(
                        error = e,
                        message = e.message ?: "Error al obtener el dolar blue"
                    )
                )
            }
        }
    }

}