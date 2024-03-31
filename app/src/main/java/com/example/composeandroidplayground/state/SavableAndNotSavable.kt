package com.example.composeandroidplayground.state

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeandroidplayground.common.theme.Dimens
import com.example.composeandroidplayground.common.view.LoadingWidget
import com.example.composeandroidplayground.common.view.SpacerHeight

@Composable
fun SavableAndNotSavableScreen() {
    val viewModel = hiltViewModel<SimpleStateViewModel>()
    val uiState = viewModel.viewState.collectAsState()

    SavableAndNotSavableScreenContent(uiState, onInit = {
        viewModel.onTriggerEvent(SimpleStateEvent.Init)
    }, onClick = {
        viewModel.onTriggerEvent(SimpleStateEvent.Select(it))
    })
}

@Composable
fun SavableAndNotSavableScreenContent(
    uiState: State<SimpleStateUIState>,
    onInit: () -> Unit,
    onClick: (String) -> Unit
) {
    val hasRunOnce = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!hasRunOnce.value) {
            onInit.invoke()
            hasRunOnce.value = true
        }
    }

    when (val uiStateValue = uiState.value) {
        SimpleStateUIState.Error -> {

        }

        SimpleStateUIState.Loading -> {
            LoadingWidget()
        }

        is SimpleStateUIState.Result -> {
            val data = uiStateValue.list
            val selectedDataNonSavable = remember { mutableStateOf("") }
            val selectedDataSavable = rememberSaveable { mutableStateOf("") }

            Column {
                Text("Non Savable")
                LazyColumn {
                    items(data.size) {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    selectedDataNonSavable.value = data[it]
                                }
                                .padding(vertical = Dimens.medium),
                            text = data[it],
                            fontWeight = if (selectedDataNonSavable.value == data[it]) FontWeight.Bold else FontWeight.Light
                        )
                    }
                }

                SpacerHeight(
                    Dimens.dp32
                )

                Text("Savable")
                LazyColumn {
                    items(data.size) {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    selectedDataSavable.value = data[it]
                                }
                                .padding(vertical = Dimens.medium),
                            text = data[it],
                            fontWeight = if (selectedDataSavable.value == data[it]) FontWeight.Bold else FontWeight.Light
                        )
                    }
                }

                SpacerHeight(
                    Dimens.dp32
                )

                Text("ViewModel")
                println("TAG: ${uiStateValue.selected.value}")
                LazyColumn {
                    items(data.size) {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    onClick.invoke(data[it])
                                }
                                .padding(vertical = Dimens.medium),
                            text = data[it],
                            fontWeight = if (uiStateValue.selected.value == data[it]) FontWeight.Bold else FontWeight.Light
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun SavableAndNotSavablePreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        SavableAndNotSavableScreenContent(
            mutableStateOf(
                SimpleStateUIState.Result(
                    mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4")
                )
            ),
            onInit = {

            }, onClick = {

            }
        )
    }
}