package com.varqulabs.dolarblue.auth.presentation.login

sealed class LoginUiEffect {
    data class ShowError(val message: String) : LoginUiEffect()
}