package com.varqulabs.dolarblue.history.data.repository

import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionHistoryDao
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.mapToModel
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversionsHistoryRepositoryImpl(
    private val conversionHistoryDao: ConversionHistoryDao
): ConversionsHistoryRepository {
    override suspend fun getConversionsHistory(): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.getConversionsHistory().map { it.map { it.mapToModel() } }
    }
}