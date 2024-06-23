package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository

class UpdateFavoriteCurrencyFromPreferences(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(currency: String) {
        preferencesRepository.putPreference(
            key = PreferenceKey.FAVOURITE_CURRENCY_KEY,
            value = currency,
        )
    }
}