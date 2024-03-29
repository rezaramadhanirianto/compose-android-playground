package com.example.composeandroidplayground.common.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeandroidplayground.common.theme.Dimens


@Composable
fun SpacerWidth(
    width: Dp = Dimens.medium
) {
    Spacer(modifier = Modifier.width(width))
}
@Composable
fun SpacerHeight(
    height: Dp = Dimens.medium
) {
    Spacer(modifier = Modifier.height(height))
}