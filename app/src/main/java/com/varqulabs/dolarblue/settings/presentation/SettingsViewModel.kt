package com.varqulabs.dolarblue.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.core.domain.useCases.GetArgentinianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetBolivianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetDollarNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetFavoriteCurrencyByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetNotificationsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateArgentinianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateBolivianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDefaultThemeEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDollarNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateFavoriteCurrencyFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateNotificationsEnabledFromPreferences
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.Init
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnBack
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnChangeMyName
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnChangePasssword
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnLogout
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnSelectFavoriteCurrency
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnSignIn
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateArgentinianNewsEnabled
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateBolivianNewsEnabled
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateDarkThemeEnabled
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateDoNotDisturb
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateDollarNewsEnabled
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect.GoBack
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect.NavigateToChangeName
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect.NavigateToChangePassword
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect.NavigateToLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getFavoriteCurrencyByPreferences: GetFavoriteCurrencyByPreferences,
    private val updateFavoriteCurrencyFromPreferences: UpdateFavoriteCurrencyFromPreferences,
    private val getNotificationsEnabledByPreferences: GetNotificationsEnabledByPreferences,
    private val updateNotificationsEnabledFromPreferences: UpdateNotificationsEnabledFromPreferences,
    private val getBolivianNewsEnabledByPreferences: GetBolivianNewsEnabledByPreferences,
    private val updateBolivianNewsEnabledFromPreferences: UpdateBolivianNewsEnabledFromPreferences,
    private val getDollarNewsEnabledByPreferences: GetDollarNewsEnabledByPreferences,
    private val updateDollarNewsEnabledFromPreferences: UpdateDollarNewsEnabledFromPreferences,
    private val getArgentinianNewsEnabledByPreferences: GetArgentinianNewsEnabledByPreferences,
    private val updateArgentinianNewsEnabledFromPreferences: UpdateArgentinianNewsEnabledFromPreferences,
    private val updateDefaultThemeEnabledFromPreferences: UpdateDefaultThemeEnabledFromPreferences
) : ViewModel(), MVIContract<SettingsState, SettingsEvent, SettingsUiEffect> by mviDelegate(SettingsState()) {

    override fun eventHandler(event: SettingsEvent) {
        when (event) {
            is OnLogout -> {}
            is OnChangePasssword -> emitNavigationToChangePassword()
            is OnChangeMyName -> emitNavigationToChangeName()
            is OnSignIn -> emitNavigationToLogin()
            is OnBack -> emitNavigationBack()
            is Init -> init()
            is UpdateDarkThemeEnabled -> updateDarkThemeEnabled(event.newValue)
            is OnSelectFavoriteCurrency -> updateFavoriteCurrency(event.currency)
            is UpdateDoNotDisturb -> updateIsNotificationsEnabled()
            is UpdateBolivianNewsEnabled -> updateIsBolivianNewsEnabled()
            is UpdateDollarNewsEnabled -> updateIsDollarNewsEnabled()
            is UpdateArgentinianNewsEnabled -> updateIsArgentinianNewsEnabled()
        }
    }

    private fun emitNavigationToLogin() = viewModelScope.emitEffect(NavigateToLogin)

    private fun emitNavigationToChangeName() = viewModelScope.emitEffect(NavigateToChangeName)

    private fun emitNavigationToChangePassword() = viewModelScope.emitEffect(NavigateToChangePassword)

    private fun emitNavigationBack() = viewModelScope.emitEffect(GoBack)

    private fun init() {
        getFavoriteCurrency()
        getIsNotificationsEnabled()
        getIsBolivianNewsEnabled()
        getIsDollarNewsEnabled()
        getIsArgentinianNewsEnabled()
    }

    private fun getFavoriteCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteCurrencyByPreferences.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isError = false,
                            isLoading = false,
                            favoriteCurrency = it
                        )
                    }
                }
            }
        }
    }

    private fun getIsNotificationsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotificationsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isError = false,
                            isLoading = false,
                            doNotDisturbEnabled = it
                        )
                    }
                }
            }
        }
    }

    private fun getIsBolivianNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getBolivianNewsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isError = false,
                            isLoading = false,
                            bolivianNewsEnabled = it
                        )
                    }
                }
            }
        }
    }

    private fun getIsDollarNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getDollarNewsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isError = false,
                            isLoading = false,
                            dollarNewsEnabled = it
                        )
                    }
                }
            }
        }
    }

    private fun getIsArgentinianNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getArgentinianNewsEnabledByPreferences.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isError = false,
                            isLoading = false,
                            argentinianNewsEnabled = it
                        )
                    }
                }
            }
        }
    }

    private fun updateFavoriteCurrency(newValue: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteCurrencyFromPreferences.execute(newValue).collect()
        }
    }

    private fun updateDarkThemeEnabled(newValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateDefaultThemeEnabledFromPreferences.execute(newValue).collect()
        }
    }

    private fun updateIsNotificationsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            updateNotificationsEnabledFromPreferences.execute(!uiState.value.doNotDisturbEnabled).collect()
        }
    }

    private fun updateIsBolivianNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            updateBolivianNewsEnabledFromPreferences.execute(!uiState.value.bolivianNewsEnabled).collect()
        }
    }

    private fun updateIsDollarNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            updateDollarNewsEnabledFromPreferences.execute(!uiState.value.dollarNewsEnabled).collect()
        }
    }

    private fun updateIsArgentinianNewsEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            updateArgentinianNewsEnabledFromPreferences.execute(!uiState.value.argentinianNewsEnabled).collect()
        }
    }

    private fun <T> updateUiStateForDataState(
        dataState: DataState<T>,
        onSuccess: (T) -> Unit
    ) {
        updateUi {
            when (dataState) {
                is DataState.Error, DataState.NetworkError -> copy(isError = true, isLoading = false)
                is DataState.Loading -> copy(isError = false, isLoading = true)
                is DataState.Success -> {
                    onSuccess(dataState.data)
                    copy()
                }
            }
        }
    }

}