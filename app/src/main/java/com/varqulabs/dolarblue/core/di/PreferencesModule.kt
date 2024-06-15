package com.varqulabs.dolarblue.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.data.local.preferences.repository.PreferencesRepositoryImpl
import com.varqulabs.dolarblue.core.domain.usecases.GetDefaultThemeByPreferencesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private val PREFERENCES_DATA_STORE = "PreferencesDataStore"

// TODO @JuanJo - Esto creo que no deberia ir en Core, sino en el Modulo app, de la App Android
@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = emptyList(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_DATA_STORE)},
        )
    }

    @Provides
    @Singleton
    fun provideDataStorePreferences(preferences: DataStore<Preferences>): PreferencesRepository {
        return PreferencesRepositoryImpl(preferences)
    }

    @Provides
    @Singleton
    fun provideGetDarkThemeByPreferencesUseCase(preferences: PreferencesRepository): GetDefaultThemeByPreferencesUseCase {
        return GetDefaultThemeByPreferencesUseCase(preferences)
    }

}