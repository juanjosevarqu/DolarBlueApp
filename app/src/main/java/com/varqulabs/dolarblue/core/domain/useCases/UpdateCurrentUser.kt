package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.USER_SESSION
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.user.data.mappers.toUserSerializable
import com.varqulabs.dolarblue.core.user.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UpdateCurrentUser(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
): UseCase<User, Unit>(dispatcher) {

    override suspend fun executeData(input: User): Flow<Unit> {
        preferencesRepository.putPreference(
            key = USER_SESSION,
            value = Json.encodeToString(input.toUserSerializable())
        )
        return emptyFlow()
    }
}