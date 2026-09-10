package com.laurynas.evento.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : Any>(initialState: State) : ViewModel() {

    private val internalState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val stateFlow: StateFlow<State> = internalState.asStateFlow()
    val state: State
        get() = stateFlow.value

    protected fun setState(handler: State.() -> State) {
        internalState.update { handler(internalState.value) }
    }

}