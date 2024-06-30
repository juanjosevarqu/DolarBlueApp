package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.model.Conversion
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository

class UpdateConversionUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(conversion: Conversion) {
        repository.updateConversion(conversion)
    }
}