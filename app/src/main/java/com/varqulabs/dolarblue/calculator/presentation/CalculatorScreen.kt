package com.varqulabs.dolarblue.calculator.presentation

import androidx.compose.runtime.Composable
import com.varqulabs.dolarblue.core.presentation.views.SimpleScreen

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    eventHandler: (CalculatorEvent) -> Unit,
) {

    SimpleScreen(
        text = "Hello",
        textButton = "Click Me para el Historial"
    ) {
        eventHandler(CalculatorEvent.PressNumber(1.0))
    }

}