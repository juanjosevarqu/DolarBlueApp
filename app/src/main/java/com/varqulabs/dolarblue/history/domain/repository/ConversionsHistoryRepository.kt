package com.varqulabs.dolarblue.history.domain.repository

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import kotlinx.coroutines.flow.Flow

interface ConversionsHistoryRepository {
    suspend fun getConversionsHistoryFlow(): Flow<List<ConversionsHistory>>

    suspend fun searchConversionsHistoryByQuery(querySearch: String): Flow<List<ConversionsHistory>>

    // FUNCIONAL 1
    suspend fun search1(querySearch: String): Flow<List<ConversionsHistory>>

    // FUNCIONAL 2
    suspend fun search2(querySearch: String): Flow<List<ConversionsHistory>>
}