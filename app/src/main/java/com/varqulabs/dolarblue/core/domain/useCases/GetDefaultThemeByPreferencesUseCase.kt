package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.IS_DEFAULT_THEME_KEY
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository

class GetDefaultThemeByPreferencesUseCase(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(): Boolean {
        return preferencesRepository.getNormalPreference(
            key = IS_DEFAULT_THEME_KEY,
            defaultValue = false,
        )
    }
}