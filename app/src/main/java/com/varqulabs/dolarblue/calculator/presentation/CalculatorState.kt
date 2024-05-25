package com.varqulabs.dolarblue.calculator.presentation

import androidx.compose.runtime.Stable

@Stable
data class CalculatorState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val dolarActual: Double = 0.0,
    val pesosActual: Double = 0.0,
    val bolivianosActual: Double = 0.0,
    val lastDateUpdated: String = "",
    val equivalenciaEnDolares: Double = 0.0,
    val equivalenciaEnPesos: Double = 0.0,
    val equivalenciaEnBolivianos: Double = 0.0,
)