package com.laurynas.evento.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
object AppDimensions {
    val iconSize: Dp = 24.dp

    //  Spacings
    val spacing2: Dp = 2.dp
    val spacing4: Dp = 4.dp
    val spacing8: Dp = 8.dp
    val spacing12: Dp = 12.dp
    val spacing16: Dp = 16.dp
    val spacing20: Dp = 20.dp
    val spacing24: Dp = 24.dp
    val spacing32: Dp = 32.dp
    val spacing40: Dp = 40.dp

    val toolbarHeight: Dp = 40.dp
    val spacingScreenHorizontal: Dp = spacing16

    //  Text Size
    val textHeadlineLarge: TextUnit = 22.sp
    val textHeadlineLargeLineHeight: TextUnit = 28.sp
    val textHeadlineMedium: TextUnit = 18.sp
    val textHeadlineMediumLineHeight: TextUnit = 22.sp
    val textNormal: TextUnit = 14.sp
    val textNormalLineHeight: TextUnit = 22.sp
    val textSmall: TextUnit = 12.sp
    val textSmallLineHeight: TextUnit = 16.sp
    val textExtraSmall: TextUnit = 10.sp
    val textExtraSmallLineHeight: TextUnit = 14.sp
}