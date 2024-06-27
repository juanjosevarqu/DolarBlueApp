package com.varqulabs.dolarblue.history.domain.useCases

import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository

class AddConversionFavoriteUseCase(
    private val repository: ConversionsHistoryRepository
) {
    suspend operator fun invoke(conversionId: Int, isFavorite: Boolean) {
        repository.addConversionFavorite(conversionId, isFavorite)
    }
}