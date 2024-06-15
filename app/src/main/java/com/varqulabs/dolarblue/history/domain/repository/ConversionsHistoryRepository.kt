package com.varqulabs.dolarblue.history.domain.repository

import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory

interface ConversionsHistoryRepository {
    suspend fun getConversionsHistory(): List<ConversionsHistory>
}