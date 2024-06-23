package com.varqulabs.dolarblue.history.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsHistoryRelation
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsWithCurrentExchangeRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionsHistoryDao {

    @Transaction
    @Query("SELECT * FROM current_exchange_rate_table")
    fun getConversionsHistoryFlow(): Flow<List<ConversionsHistoryRelation>>

    @Transaction
    @Query("SELECT * FROM current_exchange_rate_table")
    fun getConversionsHistory(): List<ConversionsHistoryRelation>

    @Query("""
        SELECT * FROM conversion_table
        WHERE name LIKE '%'|| :querySearch ||'%'
        OR date LIKE '%'|| :querySearch ||'%'
    """)
    fun searchConversionsByQuery(querySearch: String): Flow<List<ConversionEntity>>

    @Transaction
    @Query("""
        SELECT * FROM current_exchange_rate_table
        WHERE id = :id
    """)
    fun getCurrentExchangeRateById(id: Int): Flow<ConversionsHistoryRelation>

    // FUNCIONAL 1
    @Transaction
    @Query("""
        SELECT * FROM current_exchange_rate_table
        WHERE id IN (
            SELECT DISTINCT currentExchangeId
            FROM conversion_table
            WHERE name LIKE '%' || :querySearch || '%'
        )
    """)
    fun search1(querySearch: String): Flow<List<ConversionsHistoryRelation>>

    // FUNCIONAL 2
    @Transaction
    @Query("""
        SELECT conversion_table.*
        FROM current_exchange_rate_table
        JOIN conversion_table ON current_exchange_rate_table.id = conversion_table.currentExchangeId
        WHERE conversion_table.name LIKE '%' || :querySearch || '%'
     """)
    fun search2(querySearch: String): Flow<List<ConversionsWithCurrentExchangeRelation>>
}