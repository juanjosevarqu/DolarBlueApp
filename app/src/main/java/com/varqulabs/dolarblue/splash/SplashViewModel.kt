package com.varqulabs.dolarblue.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.domain.useCases.GetDefaultThemeEnabledByPreferencesUseCase
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import com.varqulabs.dolarblue.splash.SplashEvent.Init
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getIsDarkThemeByPreferences: GetDefaultThemeEnabledByPreferencesUseCase,
) : ViewModel(), MVIContract<SplashState, SplashEvent, SplashEffect> by mviDelegate(SplashState()) {

    override fun eventHandler(event: SplashEvent) {
        when (event) {
            is Init -> getIsDefaultThemeEnabled()
        }
    }

    private fun getIsDefaultThemeEnabled() {
        viewModelScope.launch(Dispatchers.IO) {
            getIsDarkThemeByPreferences.execute(Unit).collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isError = false, isLoading = true)
                        is DataState.Success -> copy(
                            isError = false,
                            isLoading = false,
                            isDarkTheme = dataState.data
                        )
                        is DataState.Error, is DataState.NetworkError -> copy(isError = true, isLoading = false)
                    }
                }
            }
        }
    }

}