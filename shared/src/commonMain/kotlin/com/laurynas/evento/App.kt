package com.laurynas.evento

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.laurynas.evento.di.AppGraph
import com.laurynas.evento.presentation.ui.groceries.rememberGroceryListDetailSceneStrategy
import com.laurynas.evento.presentation.theme.AppTheme
import com.laurynas.evento.presentation.ui.groceries.details.groceryDetailEntry
import com.laurynas.evento.presentation.ui.groceries.groceryListEntry

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun App(appGraph: AppGraph) {
    appGraph.appNavigator.initialise()
    val listDetailStrategy = rememberGroceryListDetailSceneStrategy<NavKey>()

    AppTheme {
        Scaffold { paddingValues ->
            SharedTransitionLayout {
                NavDisplay(
                    backStack = appGraph.appNavigator.backStack,
                    onBack = { appGraph.appNavigator.backStack.removeLastOrNull() },
                    sceneStrategies = listOf(listDetailStrategy),
                    sharedTransitionScope = this,
                    modifier = Modifier.padding(paddingValues),
                    entryProvider = entryProvider {
                        groceryListEntry(appGraph)
                        groceryDetailEntry(appGraph)
//                        entry<Profile> {
//                            ProfileScreen()
//                        }
                    }
                )
            }
        }
    }
}