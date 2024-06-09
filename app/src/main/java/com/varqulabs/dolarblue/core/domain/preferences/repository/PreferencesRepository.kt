package com.varqulabs.dolarblue.core.domain.preferences.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    suspend fun <T> getNormalPreference(key: Preferences.Key<T>, defaultValue: T): T

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T)

    suspend fun <T> removePreference(key: Preferences.Key<T>)

    suspend fun clearAllPreferences()

}