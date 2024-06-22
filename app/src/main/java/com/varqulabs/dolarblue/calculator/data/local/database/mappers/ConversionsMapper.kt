package com.varqulabs.dolarblue.calculator.data.local.database.mappers

import com.varqulabs.dolarblue.calculator.data.local.database.entities.ConversionEntity
import com.varqulabs.dolarblue.history.domain.model.Conversion

fun ConversionEntity.mapToModel() = Conversion(
    id = id,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    dollar = dollar,
    date = date,
    currentExchangeId = currentExchangeId
)