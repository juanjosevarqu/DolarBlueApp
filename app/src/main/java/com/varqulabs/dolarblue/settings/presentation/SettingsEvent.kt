package com.varqulabs.dolarblue.settings.presentation

import com.varqulabs.dolarblue.core.domain.enums.Currency

sealed interface SettingsEvent {

    data object Init : SettingsEvent

    data object OnBack : SettingsEvent

    data class UpdateDarkThemeEnabled(val newValue: Boolean) : SettingsEvent

    data class OnSelectFavoriteCurrency(val currency: Currency) : SettingsEvent

    data object UpdateDoNotDisturb : SettingsEvent

    data object UpdateBolivianNewsEnabled : SettingsEvent

    data object UpdateDollarNewsEnabled : SettingsEvent

    data object UpdateArgentinianNewsEnabled : SettingsEvent

    data object OnSignIn : SettingsEvent

    data object OnChangeMyName : SettingsEvent

    data object OnChangePasssword : SettingsEvent

    data object OnLogout : SettingsEvent

}