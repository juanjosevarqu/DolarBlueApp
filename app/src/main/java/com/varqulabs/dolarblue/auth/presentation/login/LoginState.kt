package com.varqulabs.dolarblue.auth.presentation.login

import com.varqulabs.dolarblue.core.presentation.ui.UiText

data class LoginState (
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isVisibleDialogConfirmEmail: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false
)