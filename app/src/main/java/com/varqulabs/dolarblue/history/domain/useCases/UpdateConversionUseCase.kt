package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository

class UpdateConversionUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(conversionId: Int, conversionName: String) {
        repository.updateConversion(conversionId, conversionName)
    }
}