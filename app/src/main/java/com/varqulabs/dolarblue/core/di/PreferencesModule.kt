package com.varqulabs.dolarblue.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.varqulabs.dolarblue.core.data.local.preferences.repository.PreferencesRepositoryImpl
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.domain.useCases.GetArgentinianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetBolivianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetCurrentUser
import com.varqulabs.dolarblue.core.domain.useCases.GetDefaultThemeEnabledByPreferencesUseCase
import com.varqulabs.dolarblue.core.domain.useCases.GetDollarNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetFavoriteCurrencyByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetNotificationsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateArgentinianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateBolivianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateCurrentUser
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDefaultThemeEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDollarNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateFavoriteCurrencyFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateNotificationsEnabledFromPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
    fun provideGetCurrentUserUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository
    ): GetCurrentUser {
        return GetCurrentUser(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateCurrentUserUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository
    ): UpdateCurrentUser {
        return UpdateCurrentUser(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetDefaultThemeByPreferencesUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository
    ): GetDefaultThemeEnabledByPreferencesUseCase {
        return GetDefaultThemeEnabledByPreferencesUseCase(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateDefaultEnabledFromPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): UpdateDefaultThemeEnabledFromPreferences {
        return UpdateDefaultThemeEnabledFromPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetFavoriteCurrencyByPreferencesUseCase(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository
    ): GetFavoriteCurrencyByPreferences {
        return GetFavoriteCurrencyByPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateFavoriteCurrencyFromPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): UpdateFavoriteCurrencyFromPreferences {
        return UpdateFavoriteCurrencyFromPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetNotificationsEnabledByPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): GetNotificationsEnabledByPreferences {
        return GetNotificationsEnabledByPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateNotificationsEnabledFromPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): UpdateNotificationsEnabledFromPreferences {
        return UpdateNotificationsEnabledFromPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetBolivianNewsEnabledByPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): GetBolivianNewsEnabledByPreferences {
        return GetBolivianNewsEnabledByPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateBolivianNewsEnabledFromPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): UpdateBolivianNewsEnabledFromPreferences {
        return UpdateBolivianNewsEnabledFromPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetDollarNewsEnabledByPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): GetDollarNewsEnabledByPreferences {
        return GetDollarNewsEnabledByPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateDollarNewsEnabledFromPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): UpdateDollarNewsEnabledFromPreferences {
        return UpdateDollarNewsEnabledFromPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetArgentinianNewsEnabledByPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): GetArgentinianNewsEnabledByPreferences {
        return GetArgentinianNewsEnabledByPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateArgentinianNewsEnabledFromPreferences(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        preferencesRepository: PreferencesRepository,
    ): UpdateArgentinianNewsEnabledFromPreferences {
        return UpdateArgentinianNewsEnabledFromPreferences(
            dispatcher = dispatcher,
            preferencesRepository = preferencesRepository
        )
    }

}