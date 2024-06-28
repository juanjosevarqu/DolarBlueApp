package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository

class DeleteConversionUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(conversionId: Int) {
        repository.deleteConversion(conversionId)
    }
}
