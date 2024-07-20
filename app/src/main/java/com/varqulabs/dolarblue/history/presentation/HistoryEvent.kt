package com.varqulabs.dolarblue.history.presentation

import com.varqulabs.dolarblue.history.domain.model.Conversion

sealed interface HistoryEvent {

    data object OnClickDrawer : HistoryEvent

    data object Init : HistoryEvent

    data object OnClickGetFavoriteConversions : HistoryEvent

    data class OnSetCurrencyColumnName(val currencyColumnName: String) : HistoryEvent

    data class OnSetSearchQuery(val searchQuery: String) : HistoryEvent

    data object OnSearchConversion : HistoryEvent

    data object OnClickClearTextField : HistoryEvent

    data class OnClickShowDialog(val conversion: Conversion) : HistoryEvent

    data object OnClickHideDialog : HistoryEvent

    data class OnSetNameConversion(val name: String) : HistoryEvent

    data class OnSetFavoriteConversion(val conversion: Conversion) : HistoryEvent

    data class OnShowSnackBar(val conversionDeleted: Conversion) : HistoryEvent

    data object UndoConversionDelete : HistoryEvent

    data class OnDeleteConversion(val conversion: Conversion?) : HistoryEvent
}