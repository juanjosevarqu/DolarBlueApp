package com.varqulabs.dolarblue.history.data.local.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.calculator.data.local.database.entities.CurrentExchangeRateEntity

data class ConversionsWithCurrentExchangeRelation(
    @Embedded
    val conversions: ConversionEntity,
    @Relation(
        entity = CurrentExchangeRateEntity::class,
        parentColumn = "currentExchangeId",
        entityColumn = "id"
    )
    val currentExchangeRate: CurrentExchangeRateEntity
)