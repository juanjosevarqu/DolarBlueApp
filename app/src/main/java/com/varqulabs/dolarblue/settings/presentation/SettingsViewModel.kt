package com.varqulabs.dolarblue.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel(), MVIContract<SettingsState, SettingsEvent, SettingsUiEffect> by mviDelegate(SettingsState()) {

    override fun eventHandler(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnLogout -> {}
            is SettingsEvent.OnChangePasssword -> emitNavigationToChangePassword()
            is SettingsEvent.OnChangeMyName -> emitNavigationToChangeName()
            is SettingsEvent.OnSignIn -> emitNavigationToLogin()
            is SettingsEvent.OnBack -> emitNavigationBack()
            is SettingsEvent.Init -> {}
            is SettingsEvent.OnToggleDarkMode -> updateUi { copy(darkThemeEnabled = !darkThemeEnabled) }
            is SettingsEvent.OnSelectFavoriteCurrency -> setFavoriteCurrency(event.currency)
            is SettingsEvent.OnToggleDoNotDisturb -> updateUi { copy(doNotDisturbEnabled = !doNotDisturbEnabled) }
            is SettingsEvent.OnToggleBolivianNotifications -> updateUi { copy(bolivianNewsEnabled = !bolivianNewsEnabled) }
            is SettingsEvent.OnToggleDollarNotifications -> updateUi { copy(dollarNewsEnabled = !dollarNewsEnabled) }
            is SettingsEvent.OnToggleArgentinianNotifications -> updateUi { copy(argentinianNewsEnabled = !argentinianNewsEnabled) }
            else -> updateUi { copy(isError = false) }
        }
    }

    private fun emitNavigationToLogin() = viewModelScope.emitEffect(SettingsUiEffect.NavigateToLogin)

    private fun emitNavigationToChangeName() = viewModelScope.emitEffect(SettingsUiEffect.NavigateToChangeName)

    private fun emitNavigationToChangePassword() = viewModelScope.emitEffect(SettingsUiEffect.NavigateToChangePassword)

    private fun emitNavigationBack() = viewModelScope.emitEffect(SettingsUiEffect.GoBack)

    private fun setFavoriteCurrency(currency: Currency) {
        updateUi { copy(favoriteCurrency = currency) }
    }


}