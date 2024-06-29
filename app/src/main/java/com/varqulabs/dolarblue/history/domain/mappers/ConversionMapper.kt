package com.varqulabs.dolarblue.history.domain.mappers

import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.domain.model.Conversion

fun Conversion.mapToEntity() = ConversionEntity(
    id, name, pesosBob, pesosArg, dollar, date, isFavorite, currentExchangeId
)