package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository

class GetDefaultThemeByPreferencesUseCase(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(): Boolean {
        return preferencesRepository.getNormalPreference(
            key = PreferenceKey.IS_DEFAULT_THEME_KEY,
            defaultValue = false,
        )
    }
}