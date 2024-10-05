package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.USER_SESSION
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.user.data.mappers.toUser
import com.varqulabs.dolarblue.core.user.data.mappers.toUserSerializable
import com.varqulabs.dolarblue.core.user.data.model.UserSerializable
import com.varqulabs.dolarblue.core.user.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GetCurrentUser(
    dispatcher: CoroutineDispatcher,
    private val preferencesRepository: PreferencesRepository
) : UseCase<Unit, User>(dispatcher) {

    override suspend fun executeData(input: Unit): Flow<User> {
        return preferencesRepository.getPreference(
            key = USER_SESSION,
            defaultValue = Json.encodeToString(User().toUserSerializable())
        ).map { Json.decodeFromString<UserSerializable>(it).toUser() }
    }
}