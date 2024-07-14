package com.varqulabs.dolarblue.calculator.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Loading
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnClickDrawer
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnClickSettings
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnRefreshDollarValue
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.UpdatePesos
import com.varqulabs.dolarblue.core.presentation.generics.top_bars.DrawerAppBar

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    eventHandler: (CalculatorEvent) -> Unit
) {

    LaunchedEffect(state.reload) { if (state.reload) eventHandler(Init) }

    LaunchedEffect(state.isLoading) { eventHandler(Loading(state.isLoading)) }

    Scaffold(
        topBar = {
            DrawerAppBar(
                title = stringResource(id = R.string.copy_calculator),
                onClickDrawer = { eventHandler(OnClickDrawer) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // TODO @JuanJo - Temporal, para navegar a los Ajustes
            Button(onClick = { eventHandler(OnClickSettings) }) {
                Text(text = "Navegar a los Ajustes desde Calculadora")
            }

            Text(
                text = "Valor del Dolar Blue:"
            )

            Text(
                text = "$${state.actualPesos}"
            )

            Text(
                text = "Ultima actualizacion:"
            )

            Text(
                text = state.lastDateUpdated
            )

            Button(onClick = { eventHandler(OnRefreshDollarValue) }) {
                Text(text = "Refrescar valor del dolar")
            }

            Button(onClick = { eventHandler(UpdatePesos) }) {
                Text(text = "Suma 10 al valor del Dolar")
            }
        }
    }
}