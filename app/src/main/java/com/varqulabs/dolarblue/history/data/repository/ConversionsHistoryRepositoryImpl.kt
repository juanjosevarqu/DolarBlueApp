package com.varqulabs.dolarblue.history.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
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

    override suspend fun updateConversion(conversionId: Int, conversionName: String) {
        conversionHistoryDao.updateConversion(conversionId, conversionName)
    }

    override suspend fun deleteConversion(conversionId: Int) {
        conversionHistoryDao.deleteConversion(conversionId)
    }

    override suspend fun addConversionFavorite(conversionId: Int, isFavorite: Boolean) {
        conversionHistoryDao.addConversionFavorite(conversionId, isFavorite)
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

    override suspend fun searchConversionsHistoryByQuery(
        columnName: String,
        querySearch: String
    ): Flow<List<ConversionsHistory>> {
        val query = """ SELECT conversion_table.* FROM current_exchange_rate_table
                JOIN conversion_table ON current_exchange_rate_table.id = conversion_table.currentExchangeId
                WHERE conversion_table.name LIKE  ?
                OR conversion_table.date LIKE  ?
                OR $columnName LIKE ? """

        val simpleQuery = SimpleSQLiteQuery(query = query, bindArgs = arrayOf("%$querySearch%", "%$querySearch%", "%$querySearch%"))

        return  conversionHistoryDao.searchConversionsHistoryByQuery(simpleQuery).map { relations ->
            relations.groupBy { relation -> relation.currentExchangeRate.id }
                .map { (id, groupedResults) ->
                    ConversionsHistory(
                        currentExchangeRate = groupedResults.first().currentExchangeRate.mapToModel(),
                        conversions = groupedResults.map { it.conversions.mapToModel() })
                }
        }
    }
}