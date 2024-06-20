package com.varqulabs.dolarblue.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.calculator.domain.useCases.GetDollarBlueUseCase
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Loading
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnHistoryClick
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnRefreshDollarValue
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.UpdatePesos
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getDollarBlueUseCase: GetDollarBlueUseCase,
) : ViewModel(), MVIContract<CalculatorState, CalculatorEvent, CalculatorUiEffect> by mviDelegate(CalculatorState()) {

    override fun eventHandler(event: CalculatorEvent) {
        when (event) {
            is Loading -> updateUi { copy(isLoading = event.isLoading) }
            is OnHistoryClick -> emitNavigationToHistory()
            is Init -> getDollarBlue()
            is OnRefreshDollarValue -> getDollarBlue()
            is UpdatePesos -> updatePesos()
        }
    }

    private fun emitNavigationToHistory() {
        viewModelScope.emitEffect(CalculatorUiEffect.NavigateToHistory)
    }

    private fun getDollarBlue() {
        viewModelScope.launch(Dispatchers.IO) {
            getDollarBlueUseCase().collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> copy(
                            actualPesos = dataState.data.blue.valueAvg,
                            lastDateUpdated = dataState.data.lastUpdate,
                            isLoading = false,
                            isError = false,
                        )
                        is DataState.Error -> copy(isError = true)
                        is DataState.NetworkError -> copy(isError = true)
                    }
                }
            }
        }
    }

    private fun updatePesos() { // TODO - Temp function to check UI state update & test dollar use case
        updateUi { copy(actualPesos = actualPesos + 10.0) }
    }

}
