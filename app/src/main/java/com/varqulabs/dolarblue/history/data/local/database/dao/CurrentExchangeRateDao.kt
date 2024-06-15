package com.varqulabs.dolarblue.history.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.varqulabs.dolarblue.history.data.local.database.entities.CurrentExchangeRateEntity

// TODO @Deivid - mover el dao al módulo de calculadora
@Dao
interface CurrentExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentExchangeRate(currentExchangeRateEntity: CurrentExchangeRateEntity)
}