package com.laurynas.evento

import androidx.compose.ui.window.ComposeUIViewController
import com.laurynas.evento.di.AppGraph
import com.laurynas.evento.domain.IosLogger
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metro.createGraphFactory

fun MainViewController() = ComposeUIViewController {
    val appGraph = createGraphFactory<AppGraph.Factory>()
        .create(IosLogger())

    App(appGraph)
}