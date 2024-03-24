package com.example.composeandroidplayground.selectable

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import com.example.composeandroidplayground.common.base.viewmodel.BaseViewModel
import com.example.composeandroidplayground.selectable.model.Selectable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class SelectableViewModel @Inject constructor() : BaseViewModel<SelectableUIState>() {
    override var state: MutableStateFlow<SelectableUIState> = MutableStateFlow(SelectableUIState.Loading)

    init {
        state.value = SelectableUIState.Simple(
            mutableStateListOf<Selectable>().apply {
                for (i in 1..10)
                    add(Selectable("test"))
            }, mutableStateMapOf()
        )
    }

    fun setSelected(selected: Selectable) {
        if (state.value is SelectableUIState.Simple) {
            val selectedSelectables = (state.value as SelectableUIState.Simple).selectedSelectables

            if (selectedSelectables.contains(selected.id)) {
                selectedSelectables.remove(selected.id)
            } else {
                selectedSelectables.put(selected.id, selected)
            }
        }
    }
}