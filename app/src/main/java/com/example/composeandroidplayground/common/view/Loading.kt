package com.example.composeandroidplayground.common.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeandroidplayground.common.theme.CAPColor
import com.example.composeandroidplayground.common.theme.ComposeAndroidPlaygroundTheme

@Composable
fun LoadingWidget() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = CAPColor.Purple40,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
@Preview()
private fun LoadingWidgetPreview() {
    ComposeAndroidPlaygroundTheme(darkTheme = false) {
        Surface {
            LoadingWidget()
        }
    }
}