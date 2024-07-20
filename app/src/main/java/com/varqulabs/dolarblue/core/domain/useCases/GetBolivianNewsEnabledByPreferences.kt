package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.BOLIVIAN_NEWS_ENABLED_KEY
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetBolivianNewsEnabledByPreferences(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
) : UseCase<Unit, Boolean>(dispatcher) {

    override suspend fun executeData(input: Unit): Flow<Boolean> {
        return preferencesRepository.getPreference(
            key = BOLIVIAN_NEWS_ENABLED_KEY,
            defaultValue = false
        )
    }
}