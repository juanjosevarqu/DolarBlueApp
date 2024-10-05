package com.varqulabs.dolarblue.splash

import androidx.compose.runtime.Stable
import com.varqulabs.dolarblue.core.user.domain.model.User

@Stable
data class SplashState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isDarkTheme: Boolean = false,
    val currentUser: User? = null
)