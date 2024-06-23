package com.varqulabs.dolarblue.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import com.varqulabs.dolarblue.history.presentation.HistoryEvent.OnClickDrawer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(

) : ViewModel(), MVIContract<HistoryState, HistoryEvent, HistoryUiEffect> by mviDelegate(HistoryState()) {

    override fun eventHandler(event: HistoryEvent) {
        when (event) {
            is OnClickDrawer -> emitOpenDrawer()
        }
    }

    private fun emitOpenDrawer() = viewModelScope.emitEffect(HistoryUiEffect.OpenDrawer)

}