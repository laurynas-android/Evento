package com.laurynas.evento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.laurynas.evento.di.AppGraph
import dev.zacsweers.metro.createGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val appGraph = (application as App).appGraph
            App(appGraph)
        }
    }
}