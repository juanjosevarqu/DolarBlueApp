package com.varqulabs.dolarblue.calculator.presentation

import androidx.compose.runtime.Stable

@Stable
data class CalculatorState(
    val reload: Boolean = true,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val actualDollar: Double = 0.0,
    val actualPesos: Double = 0.0,
    val actualBolivianos: Double = 0.0,
    val lastDateUpdated: String = "",
    val dollarEquivalency: Double = 0.0,
    val pesosEquivalency: Double = 0.0,
    val bolivianosEquivalency: Double = 0.0
)