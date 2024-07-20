package com.varqulabs.dolarblue.settings.presentation

import androidx.compose.runtime.Stable
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.core.user.domain.model.User

@Stable
data class SettingsState(
    val reload: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val favoriteCurrency: Currency = Currency.BOLIVIANO,
    val doNotDisturbEnabled: Boolean = false,
    val bolivianNewsEnabled: Boolean = false,
    val dollarNewsEnabled: Boolean = false,
    val argentinianNewsEnabled: Boolean = false,
    val currentUser: User? = null
)