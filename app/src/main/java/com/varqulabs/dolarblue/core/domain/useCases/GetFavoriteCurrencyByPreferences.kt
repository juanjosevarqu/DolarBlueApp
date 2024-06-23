package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteCurrencyByPreferences(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return preferencesRepository.getPreference(
            key = PreferenceKey.FAVOURITE_CURRENCY_KEY,
            defaultValue = "BOB",
        )
    }
}