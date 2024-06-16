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
        startDestination = Routes.History,
        modifier = modifier,
    ) {

        calculatorRoute(
            openDrawer = {
                // TODO @JuanJo - Abrir el Drawer
            },
            navigateToSettings = {
                // TODO @JuanJo - Agregar Navegacion a los Ajustes
            },
        )

        historyRoute(
            openDrawer = {
                // TODO @JuanJo - Abrir el Drawer
            },
        )
    }
}



