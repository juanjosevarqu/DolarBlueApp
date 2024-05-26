package com.varqulabs.dolarblue.calculator.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.varqulabs.dolarblue.calculator.domain.model.BlueValue
import com.varqulabs.dolarblue.core.domain.extensions.orZero

data class BlueValueDto(
    @SerializedName("value_avg") val valueAvg: Double? = 0.0,
    @SerializedName("value_buy") val valueBuy: Double? = 0.0,
    @SerializedName("value_sell") val valueSell: Double? = 0.0,
)

fun BlueValueDto.mapToModel() = BlueValue(
    valueAvg = valueAvg.orZero(),
    valueBuy = valueBuy.orZero(),
    valueSell = valueSell.orZero(),
)
