package com.varqulabs.dolarblue.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.domain.useCases.GetArgentinianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetBolivianNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetDollarNewsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.GetNotificationsEnabledByPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateArgentinianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateBolivianNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDefaultThemeEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateDollarNewsEnabledFromPreferences
import com.varqulabs.dolarblue.core.domain.useCases.UpdateNotificationsEnabledFromPreferences
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.*
import com.varqulabs.dolarblue.settings.presentation.SettingsUiEffect.*
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
            is OnSelectFavoriteCurrency -> updateUi { copy(favoriteCurrency = event.currency) } // TODO @JuanJo - Temporal
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
        getIsNotificationsEnabled()
        getIsBolivianNewsEnabled()
        getIsDollarNewsEnabled()
        getIsArgentinianNewsEnabled()
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

    private fun updateUiStateForDataState(
        dataState: DataState<Boolean>,
        onSuccess: (Boolean) -> Unit
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