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
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnForgotPasswordVisibility
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnRecoverAccountClick
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnRecoveryEmailChange
import com.varqulabs.dolarblue.auth.presentation.login.LoginScreen
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect
import com.varqulabs.dolarblue.auth.presentation.login.LoginViewModel
import com.varqulabs.dolarblue.auth.presentation.login.components.DialogConfirmEmail
import com.varqulabs.dolarblue.core.domain.extensions.ifTrue
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

            state.isLoading.ifTrue { CircularLoading() }

            state.showDialogConfirmYourEmail.ifTrue { DialogConfirmEmail() }

            state.showDialogForgotPassword.ifTrue {
                DollarBlueDialog(
                    title = stringResource(R.string.copy_title_dialog_recover_account),
                    description = stringResource(R.string.copy_description_dialog_recover_account),
                    showButtons = true,
                    leftButtonEnabled = state.recoveryEmailError == null,
                    onLeftButton = { eventHandler(OnRecoverAccountClick) },
                    onRightButton = { eventHandler(OnForgotPasswordVisibility) },
                    onDismiss = { eventHandler(OnForgotPasswordVisibility) },
                    bodyContent = {
                        DolarBlueTextField(
                            title = stringResource(R.string.title_email_field),
                            value = state.recoveryEmail,
                            error = state.recoveryEmailError?.asString(context),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = { eventHandler(OnRecoverAccountClick) }),
                            onTextChange = { eventHandler(OnRecoveryEmailChange(it)) }
                        )
                    }
                )
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
                    is LoginUiEffect.CheckYourEmail -> {
                        Toast.makeText(
                            context, it.message.asString(context), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}