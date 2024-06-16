package com.varqulabs.dolarblue.history.domain.repository

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import kotlinx.coroutines.flow.Flow

interface ConversionsHistoryRepository {
    suspend fun getConversionsHistoryFlow(): Flow<List<ConversionsHistory>>
}