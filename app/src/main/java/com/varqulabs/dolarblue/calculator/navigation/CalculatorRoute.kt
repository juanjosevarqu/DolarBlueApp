package com.varqulabs.dolarblue.calculator.navigation

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.calculator.presentation.CalculatorScreen
import com.varqulabs.dolarblue.calculator.presentation.CalculatorUiEffect
import com.varqulabs.dolarblue.calculator.presentation.CalculatorViewModel
import com.varqulabs.dolarblue.core.presentation.utils.mvi.CollectEffect
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.calculatorRoute(
    navigateToHistory: () -> Unit,
) {
    composable<Routes.Calculator> {

        val viewModel = hiltViewModel<CalculatorViewModel>()

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val eventHandler = viewModel::eventHandler
        val uiEffect = viewModel.uiEffect

        CalculatorScreen(
            state = state,
            eventHandler = eventHandler,
        )

        val context = LocalContext.current

        CollectEffect(uiEffect) {
            when (it) {
                CalculatorUiEffect.NavigateToHistory -> navigateToHistory()
                is CalculatorUiEffect.ShowError -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}