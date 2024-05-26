package com.varqulabs.dolarblue.calculator.data.remote.dto

import com.squareup.moshi.Json
import com.varqulabs.dolarblue.calculator.domain.model.BlueValue
import com.varqulabs.dolarblue.calculator.domain.model.DolarBlue

data class DolarBlueDto(
    @Json(name = "blue")
    val blue: BlueValueDto? = BlueValueDto(),
    @Json(name = "last_update")
    val last_update: String? = "",
)

fun DolarBlueDto.mapToEntity() = DolarBlue(
    blue = blue?.mapToEntity() ?: BlueValue(),
    lastUpdate = last_update.orEmpty(),
)

