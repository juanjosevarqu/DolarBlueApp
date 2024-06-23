package com.varqulabs.dolarblue.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.home.presentation.HomeScreen
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.homeRoute(
    navigateToSettings: () -> Unit,
) {
    composable<Routes.Home> {
        HomeScreen(
            navigateToSettings = navigateToSettings,
        )
    }
}