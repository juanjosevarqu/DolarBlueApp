package com.varqulabs.dolarblue.settings.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.core.domain.extensions.ifTrue
import com.varqulabs.dolarblue.core.presentation.generics.loadings.CircularLoading
import com.varqulabs.dolarblue.core.presentation.utils.mvi.CollectEffect
import com.varqulabs.dolarblue.core.presentation.utils.mvi.toTriple
import com.varqulabs.dolarblue.navigation.Routes
import com.varqulabs.dolarblue.settings.presentation.SettingsScreen
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect
import com.varqulabs.dolarblue.settings.presentation.SettingsViewModel

fun NavGraphBuilder.settingsRoute(
    navigateBack: () -> Unit
) {
    composable<Routes.Settings> {

        val viewModel = hiltViewModel<SettingsViewModel>()
        val (state, eventHandler, uiEffect) = viewModel.toTriple()

        Box(modifier = Modifier.fillMaxSize()) {

            SettingsScreen(
                state = state,
                eventHandler = eventHandler
            )

            state.isLoading.ifTrue { CircularLoading() }
        }

        CollectEffect(uiEffect = uiEffect) {
            when (it) {
                is SettingsUiEffect.GoBack -> navigateBack()
                else -> {}
            }
        }
    }
}