package com.varqulabs.dolarblue.history.data.local.database.mappers

import com.varqulabs.dolarblue.calculator.data.local.database.mappers.mapToModel
import com.varqulabs.dolarblue.history.data.local.database.entities.relations.ConversionsHistoryRelation
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory

fun ConversionsHistoryRelation.mapToModel() = ConversionsHistory(
    currentExchangeRate = currentExchangeRate.mapToModel(),
    conversions = conversions.map { it.mapToModel() }
)