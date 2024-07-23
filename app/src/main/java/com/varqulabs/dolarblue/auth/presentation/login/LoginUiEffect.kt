package com.varqulabs.dolarblue.auth.presentation.login

import com.varqulabs.dolarblue.core.presentation.ui.UiText

sealed class LoginUiEffect {

    data object GoBack : LoginUiEffect()

    data object GoRegister: LoginUiEffect()

    data class ShowError(val message: UiText) : LoginUiEffect()

    data class SuccessSendRecoverAccount(val message: UiText): LoginUiEffect()

    data class SuccessLogin(val message: UiText) : LoginUiEffect()
}