package com.varqulabs.dolarblue.settings.presentation

import com.varqulabs.dolarblue.core.domain.enums.Currency

sealed interface SettingsEvent {

    data object Init : SettingsEvent

    data object OnBack : SettingsEvent

    data object OnToggleDarkMode : SettingsEvent

    data class OnSelectFavoriteCurrency(val currency: Currency) : SettingsEvent

    data object OnToggleDoNotDisturb : SettingsEvent

    data object OnToggleBolivianNotifications : SettingsEvent

    data object OnToggleDollarNotifications : SettingsEvent

    data object OnToggleArgentinianNotifications : SettingsEvent

    data object OnSignIn : SettingsEvent

    data object OnChangeMyName : SettingsEvent

    data object OnChangePasssword : SettingsEvent

    data object OnLogout : SettingsEvent

}