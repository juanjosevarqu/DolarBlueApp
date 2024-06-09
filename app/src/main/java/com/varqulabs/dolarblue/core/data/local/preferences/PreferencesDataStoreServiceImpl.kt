package com.varqulabs.dolarblue.core.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// TODO - This extension function don't work with IOS
private val Context.dataStore by preferencesDataStore(name = "PreferencesDataStore")

// TODO - Modify & Use expect and actual to implement this function in IOS
fun createDataStore(context: Any? = null): DataStore<Preferences> {
    return (context as Context).dataStore
}

class PreferencesDataStoreServiceImpl(
    context: Context
) : PreferencesDataStoreService {

    private val dataSource = context.dataStore

    override suspend fun <T> getPreference(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> = dataSource.data.catch { exception ->
        if (exception is IOException) emit(emptyPreferences())
        else { throw exception }
    }.map { preferences ->
        val result = preferences[key] ?: defaultValue
        result
    }

    override suspend fun <T> getNormalPreference(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): T = dataSource.data.first()[key] ?: defaultValue

    override suspend fun <T> putPreference(
        key: Preferences.Key<T>,
        value: T,
    ) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun clearAllPreferences() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }
}