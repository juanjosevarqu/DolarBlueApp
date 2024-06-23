package com.varqulabs.dolarblue.history.data.repository

import com.varqulabs.dolarblue.calculator.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.data.local.database.dao.ConversionsHistoryDao
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsHistoryRelation
import com.varqulabs.dolarblue.history.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory
import com.varqulabs.dolarblue.history.domain.repository.ConversionsHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf

class ConversionsHistoryRepositoryImpl(
    private val conversionHistoryDao: ConversionsHistoryDao
): ConversionsHistoryRepository {

    override suspend fun getConversionsHistoryFlow(): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.getConversionsHistoryFlow().map { it.map { it.mapToModel() } }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun searchConversionsHistoryByQuery(querySearch: String): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.searchConversionsByQuery(querySearch)
            // Utiliza flatMapLatest para procesar el resultado del flujo anterior
            .flatMapLatest { conversions ->
                if (conversions.isEmpty()) {
                    // Si no hay conversiones, emite una lista vacía
                    flowOf(emptyList())
                } else {
                    // Si hay conversiones, agrupa las conversiones por el ID de la tasa de cambio actual
                    val groupedConversions = conversions.groupBy { it.currentExchangeId }
                    // Para cada grupo de conversiones, obtiene las relaciones y las mapea a un modelo
                    val conversionsHistory =
                        groupedConversions.map { (currentExchangeId, conversions) ->
                            conversionHistoryDao.getCurrentExchangeRateById(
                                currentExchangeId
                            ).map { relation ->
                                relation.copy(conversions = conversions).mapToModel()
                            }
                        }
                    // Combina los conversion history en uno solo y emite el resultado
                    combine(conversionsHistory) { it.toList() }
                }
            }
    }

    // FUNCIONAL 1
    override suspend fun search1(querySearch: String): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.search1(querySearch).map { relations ->
            relations.map { relation ->
                ConversionsHistoryRelation(
                    currentExchangeRate = relation.currentExchangeRate,
                    conversions = relation.conversions.filter { it.name.contains(querySearch, ignoreCase = true) }
                ).mapToModel()
            }
        }
    }

    // FUNCIONAL 2
    override suspend fun search2(querySearch: String): Flow<List<ConversionsHistory>> {
        return conversionHistoryDao.search2(querySearch).map { relations ->
            relations.groupBy { relation -> relation.currentExchangeRate.id }
                .map {( id, groupedResults) ->
                    ConversionsHistory(
                        currentExchangeRate = groupedResults.first().currentExchangeRate.mapToModel(),
                        conversions = groupedResults.map { it.conversions.mapToModel() })
                }
        }
    }
}