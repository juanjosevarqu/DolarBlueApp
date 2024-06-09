package com.varqulabs.dolarblue.core.di

import android.app.Application
import android.content.Context
import com.varqulabs.dolarblue.core.data.local.preferences.PreferencesDataStoreService
import com.varqulabs.dolarblue.core.data.local.preferences.PreferencesDataStoreServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDataStorePreferencesImpl(@ApplicationContext context: Context): PreferencesDataStoreService {
        return PreferencesDataStoreServiceImpl(context)
    }

}