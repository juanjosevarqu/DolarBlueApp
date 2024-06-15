package com.varqulabs.dolarblue.history.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsHistoryRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionHistoryDao {
    @Transaction
    @Query("SELECT * FROM current_exchange_rate_table")
    fun getConversionsHistory(): Flow<List<ConversionsHistoryRelation>>
}