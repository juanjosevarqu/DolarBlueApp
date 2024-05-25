package com.varqulabs.dolarblue.calculator.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Loading
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnHistoryClick
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnRefreshDolarValue
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.UpdatePesos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    state: CalculatorState,
    eventHandler: (CalculatorEvent) -> Unit,
) {

    LaunchedEffect(state.isLoading) { eventHandler(Loading(state.isLoading)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Calculator Screen")
                },
                actions = {
                    IconButton(onClick = { eventHandler(OnHistoryClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "Ir a historial de conversiones"
                        )
                    }
                }
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

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }

            Text(
                text = "Valor del Dolar Blue:"
            )

            Text(
                text = "$${state.pesosActual}"
            )

            Text(
                text = "Ultima actualizacion:"
            )

            Text(
                text = state.lastDateUpdated
            )

            Button(onClick = { eventHandler(OnRefreshDolarValue) }) {
                Text(text = "Refrescar valor del dolar")
            }

            Button(onClick = { eventHandler(UpdatePesos) }) {
                Text(text = "Suma 10 al valor del Dolar")
            }
        }
    }
}