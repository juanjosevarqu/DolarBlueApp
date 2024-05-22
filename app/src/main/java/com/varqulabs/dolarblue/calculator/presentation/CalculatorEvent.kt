package com.varqulabs.dolarblue.calculator.presentation

sealed interface CalculatorEvent {

    data object Init : CalculatorEvent

    data class PressNumber(val number: Double) : CalculatorEvent
}