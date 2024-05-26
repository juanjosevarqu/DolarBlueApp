package com.varqulabs.dolarblue.calculator.data.remote.dto

import com.squareup.moshi.Json
import com.varqulabs.dolarblue.calculator.domain.model.BlueValue
import com.varqulabs.dolarblue.calculator.domain.model.DollarBlue

data class DollarBlueDto(
    @Json(name = "blue") val blue: BlueValueDto? = BlueValueDto(),
    @Json(name = "last_update") val last_update: String? = "",
)

fun DollarBlueDto.mapToModel() = DollarBlue(
    blue = blue?.mapToModel() ?: BlueValue(),
    lastUpdate = last_update.orEmpty(),
)