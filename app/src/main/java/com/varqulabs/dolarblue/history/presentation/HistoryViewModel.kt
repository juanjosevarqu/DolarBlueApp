package com.varqulabs.dolarblue.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import com.varqulabs.dolarblue.history.domain.model.Conversion
import com.varqulabs.dolarblue.history.domain.model.QueryAndCurrency
import com.varqulabs.dolarblue.history.domain.useCases.DeleteConversionUseCase
import com.varqulabs.dolarblue.history.domain.useCases.DeleteExchangeRateUseCase
import com.varqulabs.dolarblue.history.domain.useCases.UpdateConversionUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetConversionsHistoryFlowUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetFavoriteConversionsHistoryUseCase
import com.varqulabs.dolarblue.history.domain.useCases.GetExchangeRateConversionCountUseCase
import com.varqulabs.dolarblue.history.domain.useCases.SearchConversionsHistoryUseCase
import com.varqulabs.dolarblue.history.presentation.HistoryEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getConversionsHistoryUseCase: GetConversionsHistoryFlowUseCase,
    private val searchConversionsHistoryUseCase: SearchConversionsHistoryUseCase,
    private val updateConversionUseCase: UpdateConversionUseCase,
    private val getFavoriteConversionsHistoryUseCase: GetFavoriteConversionsHistoryUseCase,
    private val deleteConversionUseCase: DeleteConversionUseCase,
    private val getExchangeRateConversionCountUseCase: GetExchangeRateConversionCountUseCase,
    private val deleteExchangeRateUseCase: DeleteExchangeRateUseCase
) : ViewModel(),
    MVIContract<HistoryState, HistoryEvent, HistoryUiEffect> by mviDelegate(HistoryState()) {

    override fun eventHandler(event: HistoryEvent) {
        when (event) {
            is OnClickDrawer -> emitOpenDrawer()
            is Init -> executeGetConversionHistory()
            is OnClickGetFavoriteConversions -> {
                updateUi { copy(showFavoriteConversions = !showFavoriteConversions) }
                showConversions(uiState.value.showFavoriteConversions)
            }

            is OnSetCurrencyColumnName -> setCurrencyColumnName(event.currencyColumnName)
            is OnSetSearchQuery -> setSearchQuery(event.searchQuery)
            is OnSearchConversion -> searchConversions(
                searchQuery = uiState.value.searchQuery,
                currencyColumnName = uiState.value.currencyColumnName
            )

            is OnClickClearTextField -> {
                updateUi { copy(searchQuery = "") }
                executeGetConversionHistory()
            }

            is OnClickShowDialog -> {
                updateUi {
                    copy(
                        isDialogVisible = true,
                        selectedConversion = event.conversion
                    )
                }
            }

            is OnClickHideDialog -> updateUi { copy(isDialogVisible = false) }
            is OnSetNameConversion -> setConversionName(event.name)
            is OnSetFavoriteConversion -> {
                updateUi { copy(selectedConversion = event.conversion) }
                setFavoriteConversion(!event.conversion.isFavorite)
            }

            is OnShowSnackBar -> showSnackBar(event.conversionDeleted)
            is UndoConversionDelete -> undoConversionDelete()
            is OnDeleteConversion -> confirmationDeleteConversion()
        }
    }

    // TODO crear string para informationMessage
    private fun executeGetConversionHistory() = viewModelScope.launch(Dispatchers.IO) {
        getConversionsHistoryUseCase.execute(Unit).collectLatest { dataState ->
            updateUiStateForDataState(dataState) { data ->
                updateUi {
                    copy(
                        isLoading = false,
                        conversionsHistory = data,
                        informationMessage = "No hay información por mostrar"
                    ).also { disableReload() }
                }
            }
        }
    }

    private fun showConversions(showFavoriteConversions: Boolean) {
        if (showFavoriteConversions) {
            executeGetFavoritesConversionHistory()
        } else {
            executeGetConversionHistory()
        }
    }

    // TODO crear string para informationMessage
    private fun executeGetFavoritesConversionHistory() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteConversionsHistoryUseCase.execute(Unit).collectLatest { dataState ->
            updateUiStateForDataState(dataState) { data ->
                updateUi {
                    copy(
                        isLoading = false,
                        conversionsHistory = data,
                        informationMessage = "No hay información por mostrar"
                    )
                }
            }
        }
    }

    private fun setCurrencyColumnName(currencyColumnName: String) {
        updateUi { copy(currencyColumnName = currencyColumnName) }
    }

    private fun setSearchQuery(searchQuery: String) {
        updateUi { copy(searchQuery = searchQuery) }
    }

    private fun searchConversions(searchQuery: String, currencyColumnName: String) {
        if (searchQuery.isNotBlank() || searchQuery.isNotEmpty()) {
            executeConversionHistorySearch(currencyColumnName, searchQuery)
        } else {
            executeGetConversionHistory()
        }
    }

    // TODO crear string para informationMessage
    private fun executeConversionHistorySearch(currencyColumnName: String, searchQuery: String) =
        viewModelScope.launch(Dispatchers.IO) {
            searchConversionsHistoryUseCase.execute(
                QueryAndCurrency(
                    currencyColumnName = currencyColumnName,
                    searchQuery = searchQuery
                )
            ).collectLatest { dataState ->
                updateUiStateForDataState(dataState) { data ->
                    updateUi {
                        copy(
                            isLoading = false,
                            conversionsHistory = data,
                            informationMessage = "No se encontraron coincidencias"
                        )
                    }
                }
            }
        }

    private fun setConversionName(name: String) {
        if (uiState.value.selectedConversion != null) {
            executeUpdateConversion(uiState.value.selectedConversion!!.copy(name = name))
        }
    }

    private fun setFavoriteConversion(isFavorite: Boolean) {
        if (uiState.value.selectedConversion != null) {
            executeUpdateConversion(uiState.value.selectedConversion!!.copy(isFavorite = isFavorite))
        }
    }

    private fun executeUpdateConversion(conversion: Conversion) =
        viewModelScope.launch(Dispatchers.IO) {
            updateConversionUseCase.execute(conversion).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isLoading = false,
                            isDialogVisible = false
                        )
                    }
                }
            }
        }

    private fun showSnackBar(conversionDeleted: Conversion) {
        filterConversionHistoryList(conversionDeleted)

        updateUi {
            copy(
                isSnackBarVisible = true,
                recentlyConversionDeleted = conversionDeleted,
                showFilteredList = true
            )
        }
    }

    private fun filterConversionHistoryList(conversion: Conversion) {
        val filteredConversionsHistory =
            uiState.value.conversionsHistory.map { conversionHistory ->
                if (conversionHistory.conversions.contains(conversion)) {
                    conversionHistory.copy(
                        conversions = conversionHistory.conversions.filter { it != conversion }
                    )
                } else {
                    conversionHistory
                }
            }

        updateUi { copy(filteredConversionsHistory = filteredConversionsHistory) }
    }

    private fun undoConversionDelete() {
        updateUi {
            copy(
                isSnackBarVisible = false,
                recentlyConversionDeleted = null,
                filteredConversionsHistory = emptyList(),
                showFilteredList = false,
                isHeaderVisible = true
            )
        }
    }

    private fun confirmationDeleteConversion() {
        if (uiState.value.recentlyConversionDeleted != null) {
            executeDeleteConversion(uiState.value.recentlyConversionDeleted!!)
        }
    }

    private fun executeDeleteConversion(conversion: Conversion) =
        viewModelScope.launch(Dispatchers.IO) {
            deleteConversionUseCase.execute(conversion).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        executeExchangeRateConversionCount(conversion.currentExchangeId)
                        copy(
                            isLoading = false,
                            isSnackBarVisible = false,
                            recentlyConversionDeleted = null,
                            filteredConversionsHistory = emptyList(),
                            showFilteredList = false
                        )
                    }
                }
            }
        }

    private fun executeExchangeRateConversionCount(exchangeRateId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            getExchangeRateConversionCountUseCase.execute(exchangeRateId)
                .collectLatest { dataState ->
                    updateUiStateForDataState(dataState) { data ->
                        updateUi {
                            copy(
                                isLoading = false,
                                conversionCount = data
                            )
                        }
                        conversionCountAndDeleteExchangeRate(exchangeRateId)
                    }
                }
        }

    private fun conversionCountAndDeleteExchangeRate(exchangeRateId: Int) {
        if (uiState.value.conversionCount == 0) {
            executeDeleteExchangeRate(exchangeRateId)
        }
    }

    private fun executeDeleteExchangeRate(exchangeRateId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            deleteExchangeRateUseCase.execute(exchangeRateId).collectLatest { dataState ->
                updateUiStateForDataState(
                    dataState = dataState,
                    onError = { executeGetConversionHistory() }
                ) {
                    updateUi {
                        copy(isLoading = false)
                    }
                }
            }
        }

    private fun disableReload() = updateUi { copy(reload = false) }

    private fun emitOpenDrawer() = viewModelScope.emitEffect(HistoryUiEffect.OpenDrawer)

    private fun emitError(error: String) =
        viewModelScope.emitEffect(HistoryUiEffect.ShowError(error))

    private fun <T> updateUiStateForDataState(
        dataState: DataState<T>,
        isLoading: Boolean = true,
        onError: () -> Unit = {},
        onSuccess: (T) -> Unit
    ) {
        when (dataState) {
            DataState.Loading -> {
                updateUi {
                    copy(
                        isLoading = isLoading,
                        isError = false
                    )
                }
            }

            is DataState.Success -> {
                onSuccess(dataState.data)
            }

            is DataState.Error, DataState.NetworkError -> {
                updateUi {
                    emitError(dataState.getErrorMessage())
                    copy(
                        isLoading = false,
                        isError = true
                    )
                }
                onError()
            }
        }
    }
}