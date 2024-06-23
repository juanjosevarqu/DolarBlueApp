package com.varqulabs.dolarblue.calculator.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.calculator.presentation.CalculatorScreen
import com.varqulabs.dolarblue.calculator.presentation.CalculatorUiEffect
import com.varqulabs.dolarblue.calculator.presentation.CalculatorViewModel
import com.varqulabs.dolarblue.core.presentation.generics.loadings.CircularLoading
import com.varqulabs.dolarblue.core.presentation.utils.mvi.CollectEffect
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.calculatorRoute(
    openDrawer: () -> Unit,
    navigateToSettings: () -> Unit
) {
    composable<Routes.Calculator> {

        val viewModel = hiltViewModel<CalculatorViewModel>()

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val eventHandler = viewModel::eventHandler
        val uiEffect = viewModel.uiEffect

        Box(modifier = Modifier.fillMaxSize()) {

            CalculatorScreen(
                state = state,
                eventHandler = eventHandler
            )

            if (state.isLoading) { CircularLoading() }
        }

        val context = LocalContext.current

        CollectEffect(uiEffect) {
            when (it) {
                is CalculatorUiEffect.OpenDrawer -> openDrawer()
                is CalculatorUiEffect.NavigateToSettings -> navigateToSettings()
                is CalculatorUiEffect.ShowError -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}