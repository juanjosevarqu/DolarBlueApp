package com.varqulabs.dolarblue.calculator.data.remote.dto

import com.squareup.moshi.Json
import com.varqulabs.dolarblue.calculator.domain.model.BlueValue
import com.varqulabs.dolarblue.core.domain.extensions.orZero

data class BlueValueDto(
    @Json(name = "value_avg") val value_avg: Double? = 0.0,
    @Json(name = "value_buy") val value_buy: Double? = 0.0,
    @Json(name = "value_sell") val value_sell: Double? = 0.0,
)


fun BlueValueDto.mapToEntity() = BlueValue(
    valueAvg = value_avg.orZero(),
    valueBuy = value_buy.orZero(),
    valueSell = value_sell.orZero(),
)
