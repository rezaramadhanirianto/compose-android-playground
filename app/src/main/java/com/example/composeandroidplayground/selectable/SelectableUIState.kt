package com.example.composeandroidplayground.selectable

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.example.composeandroidplayground.common.base.state.BaseUIState
import com.example.composeandroidplayground.selectable.model.Selectable

sealed interface SelectableUIState: BaseUIState {
    data object Loading : SelectableUIState

    data class Simple(
        val selectables: SnapshotStateList<Selectable>,
        val selectedSelectables: SnapshotStateMap<String, Selectable>,
    ): SelectableUIState

    data object Empty : SelectableUIState
}