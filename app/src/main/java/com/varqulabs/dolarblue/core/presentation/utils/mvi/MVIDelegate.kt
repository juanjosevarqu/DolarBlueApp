package com.varqulabs.dolarblue.core.presentation.utils.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MVIDelegate<State, Event, UiEffect> internal constructor(
    initialUiState: State,
) : MVIContract<State, Event, UiEffect> {

    // This is the state that will be observed by the Composables
    private val _uiState = MutableStateFlow(initialUiState)
    override val uiState: StateFlow<State> = _uiState.asStateFlow()

    // This is the channel that will emit the effects to the Composables
    private val _uiEffect by lazy { Channel<UiEffect>(Channel.BUFFERED) }
    override val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    // This function will be called by the ViewModel to handle the events
    override fun eventHandler(event: Event) {}

    // This function will be called by the ViewModel to set the state
    override fun setState(newState: State) {
        if (_uiState.value != newState) {
            _uiState.value = newState
        }
    }

    // This function will be called by the ViewModel to update the state
    override fun updateUi(update: State.() -> State) {
        _uiState.update { currentState ->
            val newState = currentState.update()
            if (currentState != newState) newState else currentState
        }
    }

    // This function will be called by the ViewModel to emit the effects
    override fun CoroutineScope.emitEffect(effect: UiEffect) {
        this.launch { _uiEffect.send(effect) }
    }
}

// This function will be called in the constructor of the ViewModel to create the MVIDelegate
fun <State, Event, UiEffect> mviDelegate(
    initialUiState: State,
): MVIDelegate<State, Event, UiEffect> {
    return MVIDelegate(initialUiState)
}