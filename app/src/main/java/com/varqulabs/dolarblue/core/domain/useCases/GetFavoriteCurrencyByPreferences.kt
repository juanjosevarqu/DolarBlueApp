package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.FAVOURITE_CURRENCY_KEY
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.core.domain.enums.CurrencySerializable
import com.varqulabs.dolarblue.core.domain.enums.toCurrency
import com.varqulabs.dolarblue.core.domain.enums.toSerializable
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GetFavoriteCurrencyByPreferences(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
) : UseCase<Unit, Currency>(dispatcher) {

    override suspend fun executeData(input: Unit): Flow<Currency> {
        return preferencesRepository.getPreference(
            key = FAVOURITE_CURRENCY_KEY,
            defaultValue = Json.encodeToString(Currency.BOLIVIANO.toSerializable())
        ).map { Json.decodeFromString<CurrencySerializable>(it).toCurrency() }
    }
}