package com.laurynas.evento.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.laurynas.evento.presentation.theme.AppTheme
import com.laurynas.evento.presentation.ui.groceries.LocalBackButtonVisibility

@Composable
fun AppScreen(
    title: String?,
    backgroundColor: Color = Color.White,
    onBackClick: (() -> Unit)? = null,
    contentPaddingValues: PaddingValues = PaddingValues(horizontal = AppTheme.dimen.spacingScreenHorizontal),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    toolbarEnd: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(contentPaddingValues),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        AppToolbar(
            onBackClick = if (LocalBackButtonVisibility.current) onBackClick else null,
            title = title,
            end = toolbarEnd
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(AppTheme.dimen.spacing20))
        content()
    }
}