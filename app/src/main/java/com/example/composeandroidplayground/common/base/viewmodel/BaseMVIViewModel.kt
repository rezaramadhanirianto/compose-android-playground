package com.example.composeandroidplayground.common.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composeandroidplayground.common.base.state.BaseEventState
import com.example.composeandroidplayground.common.base.state.BaseUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseMVIViewModel<UIState: BaseUIState, Event: BaseEventState> : ViewModel() {
    private val _viewState = MutableStateFlow(createDefaultViewState())
    val viewState = _viewState.asStateFlow()

    abstract fun createDefaultViewState(): UIState
    abstract fun onTriggerEvent(event: Event)


    fun updateState(viewState: UIState) {
        _viewState.value = viewState
    }
}