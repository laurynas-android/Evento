package com.laurynas.evento.presentation.ui.groceries

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laurynas.evento.data.model.GroceryItem
import com.laurynas.evento.data.repository.GroceryRepository
import com.laurynas.evento.domain.Logger
import com.laurynas.evento.extensions.runCatchingCoroutine
import com.laurynas.evento.navigation.AppNavigator
import com.laurynas.evento.navigation.Route
import com.laurynas.evento.presentation.base.BaseViewModel
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Inject
class GroceryListViewModel(
    private val repository: GroceryRepository,
    private val appNavigator: AppNavigator,
    private val logger: Logger
) : BaseViewModel<GroceryListViewModel.State>(State()) {

    @Immutable
    data class State(
        val items: List<GroceryItem>? = null
    )

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            runCatchingCoroutine { repository.getItems() }
                .onSuccess {
                    setState { copy(items = it) }
                }.onFailure {
                    logger.error("Failed to load grocery list", it)
                    setState { copy(items = emptyList()) }
                }
        }
    }

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            val newItem = GroceryItem(id = "", name = name, quantity = quantity)
            repository.addItem(newItem)
            loadItems()
        }
    }

    fun removeItem(itemId: String) {
        viewModelScope.launch {
            repository.removeItem(itemId)
            loadItems()
        }
    }

    fun onProductClick(itemId: String) {
        appNavigator.navigateTo(Route.GroceryItemDetails(itemId), isSingle = true)
    }
}
