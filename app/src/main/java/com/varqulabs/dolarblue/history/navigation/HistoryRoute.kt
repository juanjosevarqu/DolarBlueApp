package com.varqulabs.dolarblue.history.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.history.presentation.ConversionHistoryScreen
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.historyRoute(goBack: () -> Unit) {
    composable<Routes.History> {
        ConversionHistoryScreen(
            goToHome = goBack,
        )
    }
}