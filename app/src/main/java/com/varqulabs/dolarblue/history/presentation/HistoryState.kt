package com.varqulabs.dolarblue.history.presentation

import androidx.compose.runtime.Stable
import com.varqulabs.dolarblue.core.domain.enums.CurrencyTab
import com.varqulabs.dolarblue.history.domain.model.Conversion
import com.varqulabs.dolarblue.history.domain.model.ConversionsHistory

@Stable
data class HistoryState(
    val reload: Boolean = true,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val conversionsHistory: List<ConversionsHistory> = emptyList(),
    val filteredConversionsHistory: List<ConversionsHistory> = emptyList(),
    val isDialogVisible: Boolean = false,
    val selectedConversion: Conversion? = null,
    val showFavoriteConversions: Boolean = false,
    val currencyColumnName: String = CurrencyTab.BOB.columnName,
    val searchQuery: String = "",
    val conversionCount: Int = 0,
    val informationMessage: String = "",
    val isSnackBarVisible: Boolean = false,
    val recentlyConversionDeleted: Conversion? = null,
    val showFilteredList: Boolean = false,
    val isHeaderVisible: Boolean = true
)
