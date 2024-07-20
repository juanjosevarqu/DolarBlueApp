package com.varqulabs.dolarblue.history.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.varqulabs.dolarblue.calculator.data.local.database.dao.ConversionDao
import com.varqulabs.dolarblue.calculator.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsWithCurrentExchangeRelation
import com.varqulabs.dolarblue.history.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.domain.mappers.mapToEntity
import com.varqulabs.dolarblue.history.domain.model.Conversion
import com.varqulabs.dolarblue.history.domain.model.QueryAndCurrency
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConversionsHistoryRepositoryImpl(
    private val conversionHistoryDao: ConversionsHistoryDao,
    private val conversionDao: ConversionDao
) : ConversionsHistoryRepository {

    override suspend fun getConversionsHistoryFlow(): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.getConversionsHistoryFlow().map { it.map { it.mapToModel() } }
    }

    override suspend fun getFavoriteConversionsHistory(): Flow<List<ConversionsHistory>> {
        return getGroupedConversions(conversionHistoryDao.getFavoriteConversionsHistory())
    }

    override suspend fun updateConversion(conversion: Conversion) {
        conversionHistoryDao.updateConversion(conversion.mapToEntity())
    }

    override suspend fun getExchangeRateConversionCount(exchangeRateId: Int): Flow<Int> {
        return conversionHistoryDao.getExchangeRateConversionCount(exchangeRateId)
    }

    override suspend fun deleteExchangeRate(exchangeRateId: Int) {
        conversionHistoryDao.deleteExchangeRate(exchangeRateId)
    }

    override suspend fun deleteConversion(conversion: Conversion) {
        conversionHistoryDao.deleteConversion(conversion.mapToEntity())
    }

    override suspend fun searchConversionsHistoryByQueryAndCurrency(
        queryAndCurrency: QueryAndCurrency
    ): Flow<List<ConversionsHistory>> {
        val query = """ SELECT conversion_table.* FROM current_exchange_rate_table
                JOIN conversion_table ON current_exchange_rate_table.id = conversion_table.currentExchangeId
                WHERE conversion_table.name LIKE  ?
                OR ${queryAndCurrency.currencyColumnName} LIKE ? """

        val simpleQuery = SimpleSQLiteQuery(query = query, bindArgs = arrayOf("%${queryAndCurrency.searchQuery}%", "%${queryAndCurrency.searchQuery}%"))

        return getGroupedConversions(conversionHistoryDao.searchConversionsHistoryByQueryAndCurrency(simpleQuery))
    }

    override suspend fun insertConversion(conversion: Conversion) {
        conversionDao.insertConversion(conversion.mapToEntity())
    }

    private fun getGroupedConversions(daoFunction: Flow<List<ConversionsWithCurrentExchangeRelation>>) : Flow<List<ConversionsHistory>> {
        return daoFunction.map { relations ->
            relations.groupBy { relation -> relation.currentExchangeRate.id }
                .map { (_, groupedResults) ->
                    ConversionsHistory(
                        currentExchangeRate = groupedResults.first().currentExchangeRate.mapToModel(),
                        conversions = groupedResults.map { it.conversions.mapToModel() })
                }
        }
    }
}