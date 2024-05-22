package com.varqulabs.dolarblue.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(

) : ViewModel(), MVIContract<CalculatorState, CalculatorEvent, CalculatorUiEffect> by mviDelegate(CalculatorState()) {


    override fun eventHandler(event: CalculatorEvent) {
        when (event) {
            is CalculatorEvent.Init -> {
                updateUi { copy(isLoading = true) }
            }
            is CalculatorEvent.PressNumber -> {
                updateUi {
                    copy(
                        isLoading = false,
                        valorEnDolares = event.number
                    )
                }
                viewModelScope.emitEffect(CalculatorUiEffect.NavigateToHistory)
            }
        }
    }


}
