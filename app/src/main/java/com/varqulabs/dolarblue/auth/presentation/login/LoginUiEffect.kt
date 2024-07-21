package com.varqulabs.dolarblue.auth.presentation.login

sealed class LoginUiEffect {

    data object GoBack : LoginUiEffect()

    data class ShowError(val message: String) : LoginUiEffect()

    data class SuccessLogin(val message: String) : LoginUiEffect()
}