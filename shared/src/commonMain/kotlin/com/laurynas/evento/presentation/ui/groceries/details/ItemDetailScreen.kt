package com.laurynas.evento.presentation.ui.groceries.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.laurynas.evento.data.model.GroceryItem
import com.laurynas.evento.di.AppGraph
import com.laurynas.evento.navigation.Route
import com.laurynas.evento.presentation.theme.AppTheme
import com.laurynas.evento.presentation.ui.groceries.ListDetailScene
import com.laurynas.evento.presentation.ui.groceries.LocalBackButtonVisibility
import com.laurynas.evento.presentation.widgets.AppScreen
import com.laurynas.evento.presentation.widgets.AppToolbar
import kotlinx.coroutines.Dispatchers

fun EntryProviderScope<NavKey>.groceryDetailEntry(appGraph: AppGraph) {
    entry<Route.GroceryItemDetails>(
        metadata = ListDetailScene.detailPane()
    ) { screen ->
        val viewModel = remember(screen) { appGraph.regularGraph.itemDetailViewModelFactory.create(screen.itemId) }
        val state by viewModel.stateFlow.collectAsState(Dispatchers.Main.immediate)
        ItemDetailScreen(
            state = state,
            onBackClick = viewModel::onBackClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(
    state: ItemDetailViewModel.State,
    onBackClick: () -> Unit
) {
    AppScreen(
        title = state.item?.name,
        onBackClick = onBackClick
    ) {
        when {
            state.item != null -> ItemLayout(state.item)
        }
    }
}

@Composable
private fun ItemLayout(item: GroceryItem) {
    Column(
        modifier = Modifier
            .padding(horizontal = AppTheme.dimen.spacingScreenHorizontal)
    ) {
        Text("Name: ${item.name}", style = MaterialTheme.typography.headlineMedium)
        Text("Quantity: ${item.quantity}", style = MaterialTheme.typography.bodyLarge)
        Text(
            "Status: ${if (item.isBought) "Bought" else "To Buy"}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}