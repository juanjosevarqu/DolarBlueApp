package com.varqulabs.dolarblue.calculator.domain.usecases

import com.varqulabs.dolarblue.calculator.domain.model.DolarBlue
import com.varqulabs.dolarblue.calculator.domain.repository.DolarBlueRepository
import com.varqulabs.dolarblue.core.domain.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDollarBlueUseCase(
    private val blueRepository: DolarBlueRepository,
) {

    suspend operator fun invoke(): Flow<DataState<DolarBlue>> {
        return flow {
            try {
                emit(DataState.Loading)
                val dolarBlue = blueRepository.getDolarBlue()
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