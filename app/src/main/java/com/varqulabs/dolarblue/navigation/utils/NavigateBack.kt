package com.varqulabs.dolarblue.navigation.utils

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

private val NavHostController.canGoBack: Boolean
    get() = currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

internal fun NavHostController.navigateBack() {
    if (canGoBack) navigateUp()
}
