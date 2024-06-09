package com.varqulabs.dolarblue.core.data.local.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreService {

    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    suspend fun <T> getNormalPreference(key: Preferences.Key<T>, defaultValue: T): T

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T)

    suspend fun <T> removePreference(key: Preferences.Key<T>)

    suspend fun clearAllPreferences()

}