package com.varqulabs.dolarblue.calculator.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.varqulabs.dolarblue.calculator.domain.model.BlueValue
import com.varqulabs.dolarblue.calculator.domain.model.DollarBlue

data class DollarBlueDto(
    @SerializedName("blue") val blue: BlueValueDto? = BlueValueDto(),
    @SerializedName("last_update") val lastUpdate: String? = "",
)

fun DollarBlueDto.mapToModel() = DollarBlue(
    blue = blue?.mapToModel() ?: BlueValue(),
    lastUpdate = lastUpdate.orEmpty(),
)