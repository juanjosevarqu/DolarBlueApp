package com.varqulabs.dolarblue.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.varqulabs.dolarblue.auth.navigation.authRoute
import com.varqulabs.dolarblue.home.navigation.homeRoute
import com.varqulabs.dolarblue.navigation.utils.navigateBack
import com.varqulabs.dolarblue.navigation.utils.navigateTo
import com.varqulabs.dolarblue.settings.navigation.settingsRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = modifier,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {

        authRoute(
            navigateBack = { navController.navigateBack() }
        )

        homeRoute(
            navigateToSettings = { navController.navigateTo(Routes.Settings) }
        )

        settingsRoute(
            navigateBack = { navController.navigateBack() },
            navigateToLogin = { navController.navigateTo(Routes.Auth)}
        )
    }
}

// Funciones de transición

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(500)
    )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(500)
    )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(500)
    )

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(500)
    )