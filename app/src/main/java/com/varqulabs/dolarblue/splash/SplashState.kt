package com.varqulabs.dolarblue.splash

import androidx.compose.runtime.Stable

@Stable
data class SplashState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isDarkTheme: Boolean = false,
)