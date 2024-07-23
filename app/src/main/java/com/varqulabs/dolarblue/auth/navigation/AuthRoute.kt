package com.varqulabs.dolarblue.auth.navigation

import android.widget.Toast
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent
import com.varqulabs.dolarblue.auth.presentation.login.LoginScreen
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect
import com.varqulabs.dolarblue.auth.presentation.login.LoginViewModel
import com.varqulabs.dolarblue.auth.presentation.login.components.DialogConfirmEmail
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBlueTextField
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.dialog.DollarBlueDialog
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

            if (state.showDialogForgotPassword){
                DollarBlueDialog(
                    title = stringResource(R.string.title_dialog_forgot_password),
                    instructionBody = stringResource(R.string.body_dialog_forgot_password),
                    onAccept = {
                        eventHandler(LoginEvent.OnRecoverAccountClick)
                    },
                    onDismiss = {
                        eventHandler(LoginEvent.OnToggleDialogForgotPasswordClick)
                    },
                    content = {
                        DolarBlueTextField(
                            state = state.emailRecover,
                            error = state.emailRecoverError?.asString(context),
                            startIcon = null,
                            endIcon = null,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    eventHandler(LoginEvent.OnRecoverAccountClick)
                                }
                            ),
                            onTextChange = {
                                eventHandler(LoginEvent.OnEmailRecoverChange(it))
                            },
                            title = stringResource(R.string.title_email_field)
                        )
                    }
                )
            }

            if (state.isVisibleDialogConfirmEmail) {
                DialogConfirmEmail()
            }

            CollectEffect(uiEffect) {
                when (it) {
                    is LoginUiEffect.ShowError -> Toast.makeText(
                        context, it.message.asString(context), Toast.LENGTH_SHORT
                    ).show()
                    is LoginUiEffect.GoBack -> navigateBack()
                    is LoginUiEffect.SuccessLogin -> Toast.makeText(
                        context, it.message.asString(context), Toast.LENGTH_SHORT
                    ).show()
                    is LoginUiEffect.GoRegister -> {
                        //Aqui va la navegacion a la pantalla de registro
                        Toast.makeText(
                            context, "Soy registo", Toast.LENGTH_SHORT
                        ).show()
                    }
                    is LoginUiEffect.SuccessSendRecoverAccount -> {
                        Toast.makeText(
                            context, it.message.asString(context), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }
    }
}