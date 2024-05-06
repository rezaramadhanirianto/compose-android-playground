package com.example.composeandroidplayground.combineWithXml

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun RectangleMatchParentWithHeight(heightState: MutableState<Int>) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(heightState.value.dp)
                .background(Color.Cyan)
        )
}
