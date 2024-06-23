package com.varqulabs.dolarblue.history.presentation

import androidx.compose.runtime.Stable

@Stable
data class HistoryState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
