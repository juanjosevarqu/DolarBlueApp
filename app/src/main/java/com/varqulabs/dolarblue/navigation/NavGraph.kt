package com.varqulabs.dolarblue.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.varqulabs.dolarblue.home.navigation.homeRoute
import com.varqulabs.dolarblue.navigation.utils.navigateBack
import com.varqulabs.dolarblue.navigation.utils.navigateToSingleTop
import com.varqulabs.dolarblue.settings.navigation.settingsRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = modifier,
    ) {

        homeRoute(
            navigateToSettings = { navController.navigateToSingleTop(Routes.Settings) }
        )

        settingsRoute(
            navigateBack = { navController.navigateBack() }
        )
    }
}



