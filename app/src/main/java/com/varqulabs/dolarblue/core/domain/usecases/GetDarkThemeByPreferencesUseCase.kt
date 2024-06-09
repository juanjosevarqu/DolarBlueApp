package com.varqulabs.dolarblue.core.domain.usecases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository

class GetDarkThemeByPreferencesUseCase(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend fun invoke(): Boolean {
        return preferencesRepository.getNormalPreference(
            key = PreferenceKey.IS_DARK_MODE_KEY,
            defaultValue = false,
        )
    }
}