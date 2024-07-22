package com.varqulabs.dolarblue.auth.navigation

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.varqulabs.dolarblue.auth.presentation.login.LoginScreen
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect
import com.varqulabs.dolarblue.auth.presentation.login.LoginViewModel
import com.varqulabs.dolarblue.auth.presentation.login.components.DialogConfirmEmail
import com.varqulabs.dolarblue.core.presentation.generics.loadings.CircularLoading
import com.varqulabs.dolarblue.core.presentation.utils.mvi.CollectEffect
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.authRoute(
    navigateBack: () -> Unit
) {
    navigation<Routes.Auth>(startDestination = Routes.Login) {
        composable<Routes.Login> {

            val viewModel = hiltViewModel<LoginViewModel>()
            val eventHandler = viewModel::eventHandler
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            val context = LocalContext.current

            LoginScreen(
                context = context,
                state = state,
                eventHandler = eventHandler
            )

            if (state.isLoading) {
                CircularLoading()
            }

            if (state.isVisibleDialogConfirmEmail) {
                DialogConfirmEmail()
            }

            CollectEffect(uiEffect) {
                when (it) {
                    is LoginUiEffect.ShowError -> Toast.makeText(
                        context, it.message, Toast.LENGTH_SHORT
                    ).show()
                    is LoginUiEffect.GoBack -> navigateBack()
                    is LoginUiEffect.SuccessLogin -> Toast.makeText(
                        context, it.message, Toast.LENGTH_SHORT
                    ).show()
                    else -> {}
                }
            }

        }
    }
}