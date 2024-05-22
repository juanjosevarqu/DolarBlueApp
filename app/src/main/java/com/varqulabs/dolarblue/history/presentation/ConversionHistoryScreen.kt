package com.varqulabs.dolarblue.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.varqulabs.dolarblue.core.presentation.views.SimpleScreen

@Composable
fun ConversionHistoryScreen(
    modifier: Modifier = Modifier,
    goToHome: () -> Unit,
) {
    SimpleScreen(
        text = "History Conversions Screen",
        textButton = "Volver al home"
    ) {
        goToHome()
    }
}