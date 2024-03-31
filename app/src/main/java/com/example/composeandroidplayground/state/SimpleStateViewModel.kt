package com.example.composeandroidplayground.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.composeandroidplayground.common.base.state.BaseEventState
import com.example.composeandroidplayground.common.base.state.BaseUIState
import com.example.composeandroidplayground.common.base.viewmodel.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimpleStateViewModel @Inject constructor(): BaseMVIViewModel<SimpleStateUIState, SimpleStateEvent>() {
    override fun createDefaultViewState() = SimpleStateUIState.Loading

    override fun onTriggerEvent(event: SimpleStateEvent) {
        when (event) {
            SimpleStateEvent.Init -> {
                updateState(
                    SimpleStateUIState.Result(
                        mutableStateListOf(
                            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
                        )
                    )
                )
            }

            is SimpleStateEvent.Select -> {
                val state = viewState.value
                if(state is SimpleStateUIState.Result){
                    state.selected.value = event.text
                }
            }
        }
    }
}

sealed interface SimpleStateUIState : BaseUIState {
    object Loading : SimpleStateUIState
    object Error : SimpleStateUIState

    data class Result(
        val list: SnapshotStateList<String>,
        var selected: MutableState<String> = mutableStateOf("")
    ) : SimpleStateUIState
}

sealed interface SimpleStateEvent : BaseEventState {
    object Init : SimpleStateEvent
    data class Select(val text: String): SimpleStateEvent
}