package com.varqulabs.dolarblue.history.domain.mappers

import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.domain.model.Conversion

fun Conversion.mapToEntity() = ConversionEntity(
    id = id,
    name = name,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    dollar = dollar,
    date = date,
    isFavorite = isFavorite,
    currentExchangeId = currentExchangeId
)