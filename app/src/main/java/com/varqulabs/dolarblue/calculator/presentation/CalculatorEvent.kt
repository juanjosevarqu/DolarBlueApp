package com.varqulabs.dolarblue.calculator.presentation

sealed interface CalculatorEvent {

    data object OnClickDrawer : CalculatorEvent

    data object OnClickSettings : CalculatorEvent

    data class Loading(val isLoading: Boolean) : CalculatorEvent

    data object Init : CalculatorEvent

    data object OnRefreshDollarValue : CalculatorEvent

    data object UpdatePesos : CalculatorEvent

}