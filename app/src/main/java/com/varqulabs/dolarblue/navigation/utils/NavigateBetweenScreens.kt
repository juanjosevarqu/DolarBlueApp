package com.varqulabs.dolarblue.navigation.utils

import androidx.navigation.NavHostController
import com.varqulabs.dolarblue.navigation.Routes

fun NavHostController.navigateToSingleTop(route: Routes) {
    navigate(route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
