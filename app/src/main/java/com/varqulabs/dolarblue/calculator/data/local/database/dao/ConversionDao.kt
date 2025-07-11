package com.varqulabs.dolarblue.calculator.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity

@Dao
interface ConversionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversion(conversionEntity: ConversionEntity)
}