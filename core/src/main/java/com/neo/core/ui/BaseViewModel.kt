package com.neo.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Intent, Event>(
    initialUIState: State
) : ViewModel(), MVI<State, Intent, Event> {
    private var _state: MutableStateFlow<State> = MutableStateFlow(initialUIState)
    override val state: StateFlow<State>
        get() = _state

    private val _sideEffect by lazy { Channel<Event>() }
    override val sideEffect: Flow<Event>
        get() = _sideEffect.receiveAsFlow()


    override fun updateUiState(block: State.() -> State) {
        _state.update(block)
    }

    override fun CoroutineScope.emitEffect(event: Event) {
        this.launch { _sideEffect.send(event) }
    }
}