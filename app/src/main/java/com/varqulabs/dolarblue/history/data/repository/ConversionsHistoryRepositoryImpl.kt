package com.varqulabs.dolarblue.history.data.repository

import com.varqulabs.dolarblue.calculator.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.history.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversionsHistoryRepositoryImpl(
    private val conversionHistoryDao: ConversionsHistoryDao
) : ConversionsHistoryRepository {

    override suspend fun getConversionsHistoryFlow(): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.getConversionsHistoryFlow().map { it.map { it.mapToModel() } }
    }

    override suspend fun getFavoriteConversionsHistory(): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.getFavoriteConversionsHistory().map { relations ->
            relations.groupBy { relation -> relation.currentExchangeRate.id }
                .map { (id, groupedResults) ->
                    ConversionsHistory(
                        currentExchangeRate = groupedResults.first().currentExchangeRate.mapToModel(),
                        conversions = groupedResults.map { it.conversions.mapToModel() })
                }
        }
    }

    override suspend fun searchConversionsHistoryByQuery(querySearch: String): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.searchConversionsHistoryByQuery(querySearch).map { relations ->
            relations.groupBy { relation -> relation.currentExchangeRate.id }
                .map { (id, groupedResults) ->
                    ConversionsHistory(
                        currentExchangeRate = groupedResults.first().currentExchangeRate.mapToModel(),
                        conversions = groupedResults.map { it.conversions.mapToModel() })
                }
        }
    }

    override suspend fun addConversionFavorite(conversionId: Int, isFavorite: Boolean) {
        conversionHistoryDao.addConversionFavorite(conversionId, isFavorite)
    }
}