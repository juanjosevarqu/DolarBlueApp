package com.varqulabs.dolarblue.settings.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.core.domain.extensions.ifTrue
import com.varqulabs.dolarblue.core.presentation.generics.loadings.CircularLoading
import com.varqulabs.dolarblue.core.presentation.utils.mvi.CollectEffect
import com.varqulabs.dolarblue.core.presentation.utils.mvi.toTriple
import com.varqulabs.dolarblue.navigation.Routes
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent
import com.varqulabs.dolarblue.settings.presentation.SettingsScreen
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect
import com.varqulabs.dolarblue.settings.presentation.SettingsViewModel

fun NavGraphBuilder.settingsRoute(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable<Routes.Settings> {

        val viewModel = hiltViewModel<SettingsViewModel>()
        val (state, eventHandler, uiEffect) = viewModel.toTriple()

        LaunchedEffect(Unit) { eventHandler(SettingsEvent.Init) }

        Box(modifier = Modifier.fillMaxSize()) {

            SettingsScreen(
                state = state,
                eventHandler = eventHandler
            )

            state.isLoading.ifTrue { CircularLoading() }
        }

        val context = LocalContext.current

        CollectEffect(uiEffect = uiEffect) {
            when (it) {
                is SettingsUiEffect.GoBack -> navigateBack()
                is SettingsUiEffect.NavigateToLogin -> navigateToLogin()
                is SettingsUiEffect.SuccessLogout -> Toast.makeText(context, "Logout Satisfactorio", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }
}