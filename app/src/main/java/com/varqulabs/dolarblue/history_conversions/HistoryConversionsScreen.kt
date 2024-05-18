package com.varqulabs.dolarblue.history_conversions

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.varqulabs.dolarblue.home_calculator.SimpleScreen

@Composable
fun HistoryConversionsScreen(
    modifier: Modifier = Modifier,
    idUser: String,
    goToHome: () -> Unit,
) {

    Log.i("MY_TAG", "init History")


    SimpleScreen(
        text = "History Conversions Screen $idUser",
        textButton = "Volver al Home"
    ) {
        goToHome()
    }

}