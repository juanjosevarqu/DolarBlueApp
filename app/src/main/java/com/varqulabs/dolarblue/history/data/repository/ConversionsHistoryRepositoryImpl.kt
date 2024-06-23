package com.varqulabs.dolarblue.history.data.repository

import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.history.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversionsHistoryRepositoryImpl(
    private val conversionHistoryDao: ConversionsHistoryDao
): ConversionsHistoryRepository {
    override suspend fun getConversionsHistoryFlow(): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.getConversionsHistoryFlow().map { it.map { it.mapToModel() } }
    }
}