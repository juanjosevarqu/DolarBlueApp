package com.varqulabs.dolarblue.core.di

import android.content.Context
import com.varqulabs.dolarblue.core.data.local.database.DBDollarBlueApp
import com.varqulabs.dolarblue.calculator.data.local.database.dao.ConversionDao
import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.calculator.data.local.database.dao.CurrentExchangeRateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext
        context: Context
    ): DBDollarBlueApp {
        return DBDollarBlueApp.newInstance(context)
    }

    @Singleton
    @Provides
    fun provideConversionDao(dbDollarBlueApp: DBDollarBlueApp): ConversionDao {
        return dbDollarBlueApp.conversionDao()
    }

    @Singleton
    @Provides
    fun provideCurrentExchangeRateDao(dbDollarBlueApp: DBDollarBlueApp): CurrentExchangeRateDao {
        return dbDollarBlueApp.currentExchangeRateDao()
    }

    @Singleton
    @Provides
    fun provideConversionsHistoryDao(dbDollarBlueApp: DBDollarBlueApp): ConversionsHistoryDao {
        return dbDollarBlueApp.conversionsHistoryDao()
    }
}
