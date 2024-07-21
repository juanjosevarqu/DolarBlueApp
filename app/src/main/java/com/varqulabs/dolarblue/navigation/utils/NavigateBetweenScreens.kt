package com.varqulabs.dolarblue.navigation.utils

import androidx.navigation.NavHostController
import com.varqulabs.dolarblue.navigation.Routes

fun NavHostController.navigateTo(route: Routes) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToSingleTop(route: Routes) {
    navigate(route) {
        popUpTo(graph.id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
