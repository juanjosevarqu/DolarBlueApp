package com.varqulabs.dolarblue.settings.presentation

import com.varqulabs.dolarblue.core.domain.enums.Currency

sealed interface SettingsEvent {

    data object Init : SettingsEvent

    data object OnBack : SettingsEvent

    data object OnToggleDarkMode : SettingsEvent

    data class OnSelectFavoriteCurrency(val currency: Currency) : SettingsEvent

    data class UpdateDoNotDisturb(val newValue: Boolean) : SettingsEvent

    data class UpdateBolivianNewsEnabled(val newValue: Boolean) : SettingsEvent

    data class UpdateDollarNewsEnabled(val newValue: Boolean) : SettingsEvent

    data class UpdateArgentinianNewsEnabled(val newValue: Boolean) : SettingsEvent

    data object OnSignIn : SettingsEvent

    data object OnChangeMyName : SettingsEvent

    data object OnChangePasssword : SettingsEvent

    data object OnLogout : SettingsEvent

}