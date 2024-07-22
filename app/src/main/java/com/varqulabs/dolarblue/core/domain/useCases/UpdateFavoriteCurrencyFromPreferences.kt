package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.FAVOURITE_CURRENCY_KEY
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.core.domain.enums.toSerializable
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UpdateFavoriteCurrencyFromPreferences(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
): UseCase<Currency, Unit>(dispatcher) {

    override suspend fun executeData(input: Currency): Flow<Unit> {
        preferencesRepository.putPreference(
            key = FAVOURITE_CURRENCY_KEY,
            value = Json.encodeToString(input.toSerializable())
        )
        return emptyFlow()
    }
}