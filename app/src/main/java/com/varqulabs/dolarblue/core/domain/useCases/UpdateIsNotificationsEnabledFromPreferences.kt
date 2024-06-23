package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository

class UpdateIsNotificationsEnabledFromPreferences(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(isEnabled: Boolean) {
        preferencesRepository.putPreference(
            key = PreferenceKey.NOTIFICATIONS_ENABLED_KEY,
            value = isEnabled,
        )
    }
}