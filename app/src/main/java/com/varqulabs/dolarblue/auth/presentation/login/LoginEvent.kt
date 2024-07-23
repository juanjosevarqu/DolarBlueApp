package com.varqulabs.dolarblue.auth.presentation.login

import com.google.firebase.auth.AuthCredential

sealed interface LoginEvent {

    data object OnBack : LoginEvent

    data object OnRegisterClick: LoginEvent

    data object OnToggleDialogForgotPasswordClick: LoginEvent

    data class OnEmailChange(val email: String) : LoginEvent

    data class OnEmailRecoverChange(val email: String): LoginEvent

    data class OnPasswordChange(val password: String) : LoginEvent

    data object OnClickLogin : LoginEvent

    data object OnRecoverAccountClick: LoginEvent

    data class OnClickLoginWithGoogle(val credential: AuthCredential) : LoginEvent

}