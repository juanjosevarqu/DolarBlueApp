package com.varqulabs.dolarblue.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

// TODO: Regresar todo a como estaba
@Composable
fun ConversionHistoryScreen(
    modifier: Modifier = Modifier,
    goToHome: () -> Unit,
    viewModel: ConversionHistoryViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel) {
        //viewModel.addConversion()
        //viewModel.addCurrentExchange()
        viewModel.getConversionHistory()
    }
    Surface(
        Modifier
            .fillMaxSize()) {
        LazyColumn(
            //contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(viewModel.list2.value) { history ->
                Column(Modifier.padding(5.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(text = history.currentExchangeRate.date.toString())
                        Text(text = "BOB: ${history.currentExchangeRate.pesosBob}")
                        Text(text = "ARG: ${history.currentExchangeRate.pesosArg}")
                        Text(text = "Dollar: 1")
                    }
                    history.conversions.forEach { conversion ->
                        Card(onClick = { }) {
                            Row(Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "BOB: ${conversion.pesosBob}")
                                Text(text = "ARG: ${conversion.pesosArg}")
                                Text(text = "Dollar: ${conversion.dollar}")
                            }
                        }
                    }
                }

            }
        }
    }
}