package com.goofy.goober.vyasa.viewmodel

import com.goofy.goober.domain.flow.Action
import com.goofy.goober.domain.flow.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Reducer(
    initialState: State,
    private val onReduce: (from: State, action: Action, to: State) -> Unit
) {

    val state: StateFlow<State> get() = _state

    private val _state = MutableStateFlow(initialState)

    fun reduce(action: Action) {
        val oldState = _state.value
        val newState = oldState.reduce(action)

        // TODO: check if needed
        if (oldState != newState) {
            _state.value = newState
            onReduce(oldState, action, newState)
        }
    }
}
