package com.laurynas.evento.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.laurynas.evento.presentation.theme.AppTheme
import com.laurynas.evento.presentation.ui.groceries.LocalBackButtonVisibility
import com.laurynas.evento.presentation.util.pxToDp
import com.laurynas.evento.presentation.util.rememberDpToPx
import kotlin.math.max

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    onBackClick: (() -> Unit)? = null,
    title: String? = null,
    end: @Composable (() -> Unit)? = null,
) {
    AppToolbar(
        modifier = modifier,
        color = color,
        start = onBackClick?.let {
            {
                Icon(
                    modifier = Modifier.clickable(
                        onClick = it,
                        interactionSource = MutableInteractionSource(),
                        indication = ripple(bounded = false, radius = AppTheme.dimen.iconSize)
                    ),
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        middle = title?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        end = end
    )
}

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    start: @Composable (() -> Unit)? = null,
    middle: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = null,
) {
    val startWidth = remember { mutableStateOf(0) }
    val endWidth = remember { mutableStateOf(0) }
    val middleContentHorizontalPadding = rememberDpToPx(AppTheme.dimen.spacing8)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(1f),
        color = color,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            BoxWithConstraints(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimen.spacingScreenHorizontal)
                    .heightIn(min = AppTheme.dimen.toolbarHeight),
            ) {
                val maxMiddleContentWidth = remember {
                    derivedStateOf {
                        (constraints.maxWidth - (max(startWidth.value, endWidth.value) * 2)) - middleContentHorizontalPadding * 2
                    }
                }

                if (start != null) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .onSizeChanged { startWidth.value = it.width },
                        verticalArrangement = Arrangement.Center
                    ) {
                        start()
                    }
                }

                if (middle != null) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .widthIn(max = pxToDp(maxMiddleContentWidth.value)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        middle()
                    }
                }

                if (end != null) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .onSizeChanged { endWidth.value = it.width },
                        verticalArrangement = Arrangement.Center
                    ) {
                        end()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    AppTheme {
        AppToolbar(
            color = Color.White,
            start = {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
            },
            middle = {
                Text(text = "My App Toolbar")
            }
        )
    }
}
