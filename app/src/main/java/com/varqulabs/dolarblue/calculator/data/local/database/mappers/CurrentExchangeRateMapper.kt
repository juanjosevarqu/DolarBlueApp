package com.varqulabs.dolarblue.calculator.data.local.database.mappers

import com.varqulabs.dolarblue.calculator.data.local.database.entities.CurrentExchangeRateEntity
import com.varqulabs.dolarblue.history.domain.model.CurrentExchangeRate

fun CurrentExchangeRateEntity.mapToModel() = CurrentExchangeRate(
    id = id,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    date = date
)
