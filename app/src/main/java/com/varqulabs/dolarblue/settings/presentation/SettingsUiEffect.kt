package com.varqulabs.dolarblue.settings.presentation

sealed class SettingsUiEffect {

    data object GoBack : SettingsUiEffect()

    data object NavigateToLogin : SettingsUiEffect()

    data object NavigateToChangeName : SettingsUiEffect()

    data object NavigateToChangePassword : SettingsUiEffect()

    data object SuccessLogout : SettingsUiEffect()

}