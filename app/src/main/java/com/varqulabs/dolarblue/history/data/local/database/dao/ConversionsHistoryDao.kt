package com.varqulabs.dolarblue.history.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import androidx.room.Update
import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsHistoryRelation
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsWithCurrentExchangeRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionsHistoryDao {

    @Transaction
    @Query("""
        SELECT *
        FROM current_exchange_rate_table
        WHERE id IN (
            SELECT DISTINCT currentExchangeId
            FROM conversion_table
        )
    """)
    fun getConversionsHistoryFlow(): Flow<List<ConversionsHistoryRelation>>

    @Transaction
    @Query("""
        SELECT * FROM current_exchange_rate_table
        WHERE id IN (
            SELECT DISTINCT currentExchangeId
            FROM conversion_table
        )
    """)
    fun getConversionsHistory(): List<ConversionsHistoryRelation>

    @Update
    suspend fun updateConversion(conversionEntity: ConversionEntity)

    @Query("SELECT COUNT (*) FROM conversion_table WHERE currentExchangeId = :exchangeRateId")
    fun getExchangeRateConversionCount(exchangeRateId: Int): Flow<Int>

    @Query("DELETE FROM current_exchange_rate_table WHERE id = :exchangeRateId")
    suspend fun deleteExchangeRate(exchangeRateId: Int)

    @Delete
    suspend fun deleteConversion(conversionEntity: ConversionEntity)

    @Transaction
    @Query("""
        SELECT conversion_table.* FROM current_exchange_rate_table 
        JOIN conversion_table ON current_exchange_rate_table.id = conversion_table.currentExchangeId 
        WHERE conversion_table.isFavorite = 1
    """)
    fun getFavoriteConversionsHistory(): Flow<List<ConversionsWithCurrentExchangeRelation>>

    @Transaction
    @RawQuery
    fun searchConversionsHistoryByQueryAndCurrency(query: SupportSQLiteQuery): Flow<List<ConversionsWithCurrentExchangeRelation>>
}