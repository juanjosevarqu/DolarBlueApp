package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.ARGENTINIAN_NEWS_ENABLED_KEY
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class UpdateArgentinianNewsEnabledFromPreferences(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
): UseCase<Boolean, Unit>(dispatcher) {

    override suspend fun executeData(input: Boolean): Flow<Unit> {
        preferencesRepository.putPreference(
            key = ARGENTINIAN_NEWS_ENABLED_KEY,
            value = input
        )
        return emptyFlow()
    }
}