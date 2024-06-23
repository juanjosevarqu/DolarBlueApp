package com.varqulabs.dolarblue.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.varqulabs.dolarblue.calculator.navigation.calculatorRoute
import com.varqulabs.dolarblue.history.navigation.historyRoute
import com.varqulabs.dolarblue.navigation.utils.navigateBack
import com.varqulabs.dolarblue.navigation.utils.navigateToSingleTop

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Calculator,
        modifier = modifier,
    ) {

        calculatorRoute(
            navigateToHistory = {
                navController.navigateToSingleTop(Routes.History)
            },
        )

        historyRoute(
            goBack = {
                navController.navigateBack()
            },
        )
    }
}



