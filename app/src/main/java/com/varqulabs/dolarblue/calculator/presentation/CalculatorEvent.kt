package com.varqulabs.dolarblue.calculator.presentation

sealed interface CalculatorEvent {

    data object OnHistoryClick : CalculatorEvent

    data class Loading(val isLoading: Boolean) : CalculatorEvent

    data object Init : CalculatorEvent

    data object OnRefreshDolarValue : CalculatorEvent

    data object UpdatePesos : CalculatorEvent

}