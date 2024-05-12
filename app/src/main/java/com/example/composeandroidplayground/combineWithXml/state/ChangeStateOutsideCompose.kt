package com.example.composeandroidplayground.combineWithXml.state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChangeStateOutsideCompose(
    state: MutableState<ChangeStateOutsideComposeState>
) {
    ChangeStateOutsideComposeContent(state.value.text)
}

@Preview
@Composable
private fun ChangeStateOutsideComposeContent(
    text: String = ""
) {
    Box(
        modifier = Modifier
            .wrapContentHeight(align = Alignment.Top)
            .background(Color.Green)
            .padding(16.dp)
    ) {
        Text(text = text)
    }
}