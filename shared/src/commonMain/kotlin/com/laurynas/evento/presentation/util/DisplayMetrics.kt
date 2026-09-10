package com.laurynas.evento.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun rememberDpToPx(dp: Dp): Int {
    val density = LocalDensity.current
    return remember {
        with(density) { dp.roundToPx() }
    }
}

@Composable
fun rememberPxToDp(px: Int): Dp {
    val density = LocalDensity.current
    return remember {
        with(density) { px.toDp() }
    }
}

@Composable
fun dpToPx(dp: Dp): Int {
    val density = LocalDensity.current
    return with(density) { dp.roundToPx() }
}

@Composable
fun pxToDp(px: Int): Dp {
    val density = LocalDensity.current
    return with(density) { px.toDp() }
}