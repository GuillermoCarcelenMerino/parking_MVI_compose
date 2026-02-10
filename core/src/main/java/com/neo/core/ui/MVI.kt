package com.neo.core.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

 interface MVI<State, Intent, Event> {

    val state: StateFlow<State>
    val sideEffect: Flow<Event>

     fun sendIntent(intent: Intent)

    fun updateUiState(block: State.() -> State)
    fun CoroutineScope.emitEffect(event: Event)
}