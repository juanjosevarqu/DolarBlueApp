package com.varqulabs.dolarblue.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.varqulabs.dolarblue.history_conversions.HistoryConversionsScreen
import com.varqulabs.dolarblue.home_calculator.HomeCalculatorScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeCalculatorScreen(
                modifier = Modifier,
            ) {
                navController.navigate(Profile("120"))
            }
        }

        composable<Profile> { backStackEntry ->
            val idUser: String = backStackEntry.toRoute<Profile>().id
            HistoryConversionsScreen(
                idUser = idUser,
            ) {
                navController.navigate(Home)
            }
        }
    }

}