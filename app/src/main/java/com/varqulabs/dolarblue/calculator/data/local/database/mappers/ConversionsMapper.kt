package com.varqulabs.dolarblue.calculator.data.local.database.mappers

import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.domain.model.Conversion

fun ConversionEntity.mapToModel() = Conversion(
    id = id,
    name = name,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    dollar = dollar,
    date = date,
    isFavorite = isFavorite,
    currentExchangeId = currentExchangeId
)