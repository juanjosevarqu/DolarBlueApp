package com.varqulabs.dolarblue.calculator.domain.model


data class DolarBlueEntity(
    val blue: BlueValueEntity = BlueValueEntity(),
    val lastUpdate: String = "",
)

data class BlueValueEntity(
    val valueAvg: Double = 0.0,
    val valueBuy: Double = 0.0,
    val valueSell: Double = 0.0,
)