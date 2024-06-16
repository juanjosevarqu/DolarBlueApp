package com.varqulabs.dolarblue.settings.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.settingsRoute(
    navigateBack: () -> Unit,
) {
    composable<Routes.Settings> {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Settings Screen")

            Button(onClick = { navigateBack() }) {
                Text(text = "Go Back to Home Screen")
            }
        }

    }
}