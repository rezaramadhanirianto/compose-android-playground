package com.example.composeandroidplayground.selectable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composeandroidplayground.common.theme.ComposeAndroidPlaygroundTheme
import com.example.composeandroidplayground.common.view.LoadingWidget
import com.example.composeandroidplayground.selectable.model.Selectable

@Composable
fun SelectableScreenRoute() {
    val viewModel: SelectableViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    SelectableScreen(state = state.value) { data, _ ->
        viewModel.setSelected(data)
    }
}

@Composable
fun SelectableScreen(
    state: SelectableUIState,
    onSelect: (Selectable, Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (state) {
            is SelectableUIState.Loading -> {
                LoadingWidget()
            }
            
            is SelectableUIState.Simple -> {
                val list = remember {
                    state.selectables
                }
                val selected = remember {
                    state.selectedSelectables
                }
                SelectableScreenPopulateSimple(
                    list,
                    selected,
                    onSelect
                )
            }

            else -> {
                Text(text = "Hello World")
            }
        }
    }
}

@Composable
fun SelectableScreenPopulateSimple(
    selectables: List<Selectable>,
    selectedSelectables: SnapshotStateMap<String, Selectable>,
    onSelect: (Selectable, Boolean) -> Unit
) {
    LazyColumn {
        items(selectables.size, key = {
            selectables[it].id
        }) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 8.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val isSelected=
                            selectedSelectables.contains(selectables[index].id)

                    Text(text = selectables[index].text)
                    Icon(
                        imageVector = if (isSelected) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        modifier = Modifier.clickable {
                            onSelect.invoke(selectables[index], !isSelected)
                        },
                        contentDescription = "favorite"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectableScreenPreview() {
    ComposeAndroidPlaygroundTheme(
        darkTheme = false
    ) {
        Surface {
            SelectableScreen(
                state = SelectableUIState.Simple(
                    mutableStateListOf<Selectable>().apply {
                        for (i in 1..10)
                            add(Selectable("test"))
                    }, mutableStateMapOf()
                ),
                onSelect = { _, _ ->

                }
            )
        }
    }
}