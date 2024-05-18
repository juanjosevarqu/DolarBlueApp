package com.varqulabs.dolarblue.home_calculator

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeCalculatorScreen(
    modifier: Modifier = Modifier,
    goToHistory: () -> Unit,
) {

    Log.i("MY_TAG", "init Home")

    SimpleScreen(
        text = "Home Calculator Screen",
        textButton = "Ir a Historial",
    ) {
        goToHistory()
    }

}

@Composable
fun SimpleScreen(
    text: String,
    textButton: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Text(text)

        Button(onClick = onClick) {
            Text(textButton)
        }
    }
}