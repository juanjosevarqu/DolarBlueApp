package com.varqulabs.dolarblue.calculator.data.remote

import com.squareup.moshi.Json
import com.varqulabs.dolarblue.calculator.domain.model.BlueValueEntity
import com.varqulabs.dolarblue.calculator.domain.model.DolarBlueEntity
import com.varqulabs.dolarblue.core.domain.extensions.orZero

data class DolarBlueDto(
    @Json(name = "blue") val blue: BlueValueDto? = BlueValueDto(),
    @Json(name = "last_update") val last_update: String? = "",
)

data class BlueValueDto(
    @Json(name = "value_avg") val value_avg: Double? = 0.0,
    @Json(name = "value_buy") val value_buy: Double? = 0.0,
    @Json(name = "value_sell") val value_sell: Double? = 0.0,
)

fun DolarBlueDto.mapToEntity() = DolarBlueEntity(
    blue = blue?.mapToEntity() ?: BlueValueEntity(),
    lastUpdate = last_update.orEmpty(),
)

fun BlueValueDto.mapToEntity() = BlueValueEntity(
    valueAvg = value_avg.orZero(),
    valueBuy = value_buy.orZero(),
    valueSell = value_sell.orZero(),
)

