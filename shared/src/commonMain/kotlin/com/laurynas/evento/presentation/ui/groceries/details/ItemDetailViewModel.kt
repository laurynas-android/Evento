package com.laurynas.evento.presentation.ui.groceries.details

import androidx.lifecycle.viewModelScope
import com.laurynas.evento.presentation.base.BaseViewModel
import com.laurynas.evento.data.model.GroceryItem
import com.laurynas.evento.data.repository.GroceryRepository
import com.laurynas.evento.domain.Logger
import com.laurynas.evento.extensions.runCatchingCoroutine
import com.laurynas.evento.navigation.AppNavigator
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import kotlinx.coroutines.launch

@AssistedInject
class ItemDetailViewModel(
    @Assisted private val itemId: String,
    private val appNavigator: AppNavigator,
    private val repository: GroceryRepository,
    private val logger: Logger
) : BaseViewModel<ItemDetailViewModel.State>(State()) {

    @AssistedFactory
    fun interface ItemDetailViewModelFactory {
        fun create(itemId: String): ItemDetailViewModel
    }

    data class State(
       val item: GroceryItem? = null
    )


    init {
        loadItem()
    }

    fun onBackClick() {
        appNavigator.pop()
    }

    private fun loadItem() {
        viewModelScope.launch {
            runCatchingCoroutine { repository.getItemById(itemId) }
                .onSuccess {
                    logger.debug("Item loaded $it")
                    setState { copy(item = it) }
                }.onFailure {
                    logger.debug("Unable to load item $itemId")
                }
        }
    }
}
