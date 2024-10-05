package com.varqulabs.dolarblue.auth.presentation.login

import androidx.compose.runtime.Stable
import com.varqulabs.dolarblue.core.presentation.ui.UiText

@Stable
data class LoginState(
    val email: String = "",
    val emailError: UiText? = null,
    val recoveryEmail: String = "",
    val recoveryEmailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val showDialogForgotPassword: Boolean = false,
    val showDialogConfirmYourEmail: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false
)