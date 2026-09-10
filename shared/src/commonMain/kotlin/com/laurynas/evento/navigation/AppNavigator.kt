package com.laurynas.evento.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object GroceryList : Route

    @Serializable
    data class GroceryItemDetails(val itemId: String) : Route
}

class AppNavigator {
    lateinit var backStack: NavBackStack<NavKey>

    private val config = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclassesOfSealed<Route>()
            }
        }
    }

    @Composable
    fun initialise() {
        backStack = rememberNavBackStack(config, Route.GroceryList)
    }

    fun navigateTo(route: Route, isSingle: Boolean = false) {
        require(::backStack.isInitialized) { "NavManager must be initialised before navigating" }
        if (isSingle) backStack.removeAll { it::class == route::class }
        backStack.add(route)
    }

    fun pop(): Boolean {
        require(::backStack.isInitialized) { "NavManager must be initialised before navigating" }
        if (backStack.size > 1) {
            backStack.removeAt(backStack.size - 1)
            return true
        }
        return false
    }
}
