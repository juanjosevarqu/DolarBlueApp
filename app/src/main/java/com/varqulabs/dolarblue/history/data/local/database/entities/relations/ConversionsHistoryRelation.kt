package com.varqulabs.dolarblue.history.data.local.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.varqulabs.dolarblue.history.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.data.local.database.entities.CurrentExchangeRateEntity
import com.varqulabs.dolarblue.history.data.local.database.entities.mapToModel
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory

data class ConversionsHistoryRelation(
    @Embedded val currentExchangeRate: CurrentExchangeRateEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "currentExchangeId"
    )
    val conversions: List<ConversionEntity>
)

fun ConversionsHistoryRelation.mapToModel() = ConversionsHistory(
    currentExchangeRate = currentExchangeRate.mapToModel(),
    conversions = conversions.map { it.mapToModel() }
)