package com.varqulabs.dolarblue.history.presentation

sealed interface HistoryEvent {

    data object OnClickDrawer : HistoryEvent

}