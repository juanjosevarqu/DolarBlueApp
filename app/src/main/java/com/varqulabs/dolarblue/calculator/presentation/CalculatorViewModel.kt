package com.varqulabs.dolarblue.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.calculator.domain.repository.DataState
import com.varqulabs.dolarblue.calculator.domain.usecases.GetDolarBlueUseCase
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.UpdatePesos
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.Loading
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnHistoryClick
import com.varqulabs.dolarblue.calculator.presentation.CalculatorEvent.OnRefreshDolarValue
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getDolarBlueUseCase: GetDolarBlueUseCase,
) : ViewModel(), MVIContract<CalculatorState, CalculatorEvent, CalculatorUiEffect> by mviDelegate(CalculatorState()) {

    init { eventHandler(Init) }

    override fun eventHandler(event: CalculatorEvent) {
        when (event) {
            is Loading -> updateUi { copy(isLoading = event.isLoading) }
            is OnHistoryClick -> { viewModelScope.emitEffect(CalculatorUiEffect.NavigateToHistory) }
            is Init -> getDolarBlue()
            is OnRefreshDolarValue -> getDolarBlue()
            is UpdatePesos -> updateUi { copy(pesosActual = pesosActual + 10.0) }
        }
    }

    private fun getDolarBlue() {
        viewModelScope.launch(Dispatchers.IO) {
            getDolarBlueUseCase().collectLatest { dataState ->
                updateUi {
                    when (dataState) {
                        is DataState.Loading -> copy(isLoading = true)
                        is DataState.Success -> copy(
                            pesosActual = dataState.data.blue.valueAvg,
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

}
