package com.varqulabs.dolarblue.home.presentation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.calculator.navigation.calculatorRoute
import com.varqulabs.dolarblue.core.presentation.model.DrawerItem
import com.varqulabs.dolarblue.history.navigation.historyRoute
import com.varqulabs.dolarblue.home.presentation.components.DrawerContent
import com.varqulabs.dolarblue.navigation.Routes
import com.varqulabs.dolarblue.navigation.utils.navigateToSingleTop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private val drawerItems = listOf(
    DrawerItem(
        title = R.string.copy_calculator,
        icon = R.drawable.ic_launcher_foreground,
        route = Routes.Calculator
    ),
    DrawerItem(
        title = R.string.copy_history,
        icon = R.drawable.ic_launcher_foreground,
        route = Routes.History
    ),
)

@Composable
internal fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    navigateToSettings: () -> Unit
) {

    var currentDrawerRoute by remember { mutableStateOf<Routes>(Routes.Calculator) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    drawerItems = drawerItems,
                    currentRoute = currentDrawerRoute,
                ) { route ->
                    navController.navigateToSingleTop(route)
                    currentDrawerRoute = route
                    coroutineScope.launch { drawerState.close() }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.Calculator
        ) {

            calculatorRoute(
                openDrawer = { coroutineScope.launch { drawerState.open() } },
                navigateToSettings = navigateToSettings
            )

            historyRoute(
                openDrawer = { coroutineScope.launch { drawerState.open() } }
            )
        }
    }
}