package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.NOTIFICATIONS_ENABLED_KEY
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class UpdateNotificationsEnabledFromPreferences(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
): UseCase<Boolean, Unit>(dispatcher) {

    override suspend fun executeData(input: Boolean): Flow<Unit> {
        preferencesRepository.putPreference(
            key = NOTIFICATIONS_ENABLED_KEY,
            value = input
        )
        return emptyFlow()
    }
}