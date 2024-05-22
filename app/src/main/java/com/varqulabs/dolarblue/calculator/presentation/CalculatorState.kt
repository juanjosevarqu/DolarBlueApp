package com.varqulabs.dolarblue.calculator.presentation

import androidx.compose.runtime.Stable

@Stable
data class CalculatorState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val valorEnDolares: Double = 0.0,
    val valorEnPesos: Double = 0.0,
    val valorEnBolivianos: Double = 0.0,
    val equivalenciaEnDolares: Double = 0.0,
    val equivalenciaEnPesos: Double = 0.0,
    val equivalenciaEnBolivianos: Double = 0.0,
)