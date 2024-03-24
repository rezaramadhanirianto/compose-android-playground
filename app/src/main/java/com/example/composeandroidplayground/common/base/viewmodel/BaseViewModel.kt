package com.example.composeandroidplayground.common.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composeandroidplayground.common.base.state.BaseUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UIState : BaseUIState> : ViewModel() {
    abstract var state: MutableStateFlow<UIState>
}