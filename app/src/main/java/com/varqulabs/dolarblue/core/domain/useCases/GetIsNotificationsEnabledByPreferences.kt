package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetIsNotificationsEnabledByPreferences(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return preferencesRepository.getPreference(
            key = PreferenceKey.NOTIFICATIONS_ENABLED_KEY,
            defaultValue = true,
        )
    }
}