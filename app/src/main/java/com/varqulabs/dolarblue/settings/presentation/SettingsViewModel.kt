package com.varqulabs.dolarblue.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.core.domain.useCases.GetBolivianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetDollarNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetNotificationsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateBolivianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDollarNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateNotificationsEnabledFromPreferences
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getNotificationsEnabledByPreferences: GetNotificationsEnabledByPreferences,
    private val updateNotificationsEnabledFromPreferences: UpdateNotificationsEnabledFromPreferences,
    private val getBolivianNewsEnabledByPreferences: GetBolivianNewsEnabledByPreferences,
    private val updateBolivianNewsEnabledFromPreferences: UpdateBolivianNewsEnabledFromPreferences,
    private val getDollarNewsEnabledByPreferences: GetDollarNewsEnabledByPreferences,
    private val updateDollarNewsEnabledFromPreferences: UpdateDollarNewsEnabledFromPreferences
) : ViewModel(), MVIContract<SettingsState, SettingsEvent, SettingsUiEffect> by mviDelegate(SettingsState()) {

    override fun eventHandler(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnLogout -> {}
            is SettingsEvent.OnChangePasssword -> emitNavigationToChangePassword()
            is SettingsEvent.OnChangeMyName -> emitNavigationToChangeName()
            is SettingsEvent.OnSignIn -> emitNavigationToLogin()
            is SettingsEvent.OnBack -> emitNavigationBack()
            is SettingsEvent.Init -> init()
            is SettingsEvent.OnToggleDarkMode -> updateUi { copy(darkThemeEnabled = !darkThemeEnabled) }
            is SettingsEvent.OnSelectFavoriteCurrency -> setFavoriteCurrency(event.currency)
            is SettingsEvent.UpdateDoNotDisturb -> updateIsNotificationsEnabled(event.newValue)
            is SettingsEvent.UpdateBolivianNewsEnabled -> updateIsBolivianNewsEnabled(event.newValue)
            is SettingsEvent.UpdateDollarNewsEnabled -> updateIsDollarNewsEnabled(event.newValue)
            is SettingsEvent.OnToggleArgentinianNotifications -> updateUi { copy(argentinianNewsEnabled = !argentinianNewsEnabled) }
            else -> updateUi { copy(isError = false) }
        }
    }

    private fun emitNavigationToLogin() = viewModelScope.emitEffect(SettingsUiEffect.NavigateToLogin)

    private fun emitNavigationToChangeName() = viewModelScope.emitEffect(SettingsUiEffect.NavigateToChangeName)

    private fun emitNavigationToChangePassword() = viewModelScope.emitEffect(SettingsUiEffect.NavigateToChangePassword)

    private fun emitNavigationBack() = viewModelScope.emitEffect(SettingsUiEffect.GoBack)

    private fun init() {
        getIsNotificationsEnabled()
        getIsBolivianNewsEnabled()
        getIsDollarNewsEnabled()
    }

    private fun setFavoriteCurrency(currency: Currency) {
        updateUi { copy(favoriteCurrency = currency) }
    }

    private fun getIsNotificationsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotificationsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isError = false, isLoading = true)
                        is DataState.Success -> copy(
                            isError = false,
                            isLoading = false,
                            doNotDisturbEnabled = dataState.data
                        )
                        is DataState.Error, is DataState.NetworkError -> copy(isError = true, isLoading = false)
                    }
                }
            }
        }
    }

    private fun getIsBolivianNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getBolivianNewsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isError = false, isLoading = true)
                        is DataState.Success -> copy(
                            isError = false,
                            isLoading = false,
                            bolivianNewsEnabled = dataState.data
                        )
                        is DataState.Error, is DataState.NetworkError -> copy(isError = true, isLoading = false)
                    }
                }
            }
        }
    }

    private fun getIsDollarNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getDollarNewsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isError = false, isLoading = true)
                        is DataState.Success -> copy(
                            isError = false,
                            isLoading = false,
                            dollarNewsEnabled = dataState.data
                        )
                        is DataState.Error, is DataState.NetworkError -> copy(isError = true, isLoading = false)
                    }
                }
            }
        }
    }

    private fun updateIsNotificationsEnabled(newValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNotificationsEnabledFromPreferences.execute(newValue).collect()
        }
    }

    private fun updateIsBolivianNewsEnabled(newValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateBolivianNewsEnabledFromPreferences.execute(newValue).collect()
        }
    }

    private fun updateIsDollarNewsEnabled(newValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateDollarNewsEnabledFromPreferences.execute(newValue).collect()
        }
    }


}