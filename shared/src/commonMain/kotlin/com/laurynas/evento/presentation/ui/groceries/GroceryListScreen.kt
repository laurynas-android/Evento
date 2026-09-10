package com.laurynas.evento.presentation.ui.groceries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.laurynas.evento.data.model.GroceryItem
import com.laurynas.evento.di.AppGraph
import com.laurynas.evento.navigation.Route
import com.laurynas.evento.presentation.theme.AppTheme
import com.laurynas.evento.presentation.widgets.AppScreen
import kotlinx.coroutines.Dispatchers

fun EntryProviderScope<NavKey>.groceryListEntry(appGraph: AppGraph) {
    entry<Route.GroceryList>(
        metadata = ListDetailScene.listPane()
    ) {
        val viewModel = remember { appGraph.regularGraph.groceryListViewModel }
        val state by viewModel.stateFlow.collectAsState(Dispatchers.Main.immediate)
        GroceryListScreen(
            state = state,
            onAddItem = viewModel::addItem,
            onItemClick = viewModel::onProductClick,
            onRemoveItemClick = viewModel::removeItem
        )
    }
}

@Composable
fun GroceryListScreen(
    state: GroceryListViewModel.State,
    onAddItem: (String, Int) -> Unit,
    onItemClick: (String) -> Unit,
    onRemoveItemClick: (String) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    AppScreen(
        title = "Grocery list",
        toolbarEnd = {
            IconButton(onClick = { showAddDialog = !showAddDialog }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = AppTheme.dimen.spacingScreenHorizontal),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimen.spacing8)
        ) {
            when {
                state.items != null -> {
                    items(state.items) { item ->
                        GroceryItemRow(
                            item = item,
                            onClick = onItemClick,
                            onDelete = onRemoveItemClick
                        )
                    }
                }
            }

        }
    }

    if (showAddDialog) {
        AddItemDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, quantity ->
                onAddItem(name, quantity)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun GroceryItemRow(
    item: GroceryItem,
    onClick: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke(item.id) }),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                Text("Quantity: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { onDelete.invoke(item.id) }) {
                Icon(Icons.Rounded.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
private fun AddItemDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("1") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Item") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Item Name") }
                )
                TextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, quantity.toIntOrNull() ?: 1) }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}