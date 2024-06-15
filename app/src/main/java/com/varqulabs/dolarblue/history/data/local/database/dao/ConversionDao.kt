package com.varqulabs.dolarblue.history.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.varqulabs.dolarblue.history.data.local.database.entities.ConversionEntity

// TODO @Deivid - mover el dao al módulo de calculadora
@Dao
interface ConversionDao {
    @Insert()
    suspend fun insertConversion(conversionEntity: ConversionEntity)
}