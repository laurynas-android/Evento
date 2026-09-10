package com.laurynas.evento.di

import com.laurynas.evento.data.repository.GroceryRepository
import com.laurynas.evento.navigation.AppNavigator
import com.laurynas.evento.presentation.ui.groceries.GroceryListViewModel
import com.laurynas.evento.presentation.ui.groceries.details.ItemDetailViewModel
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Provides

@GraphExtension(RegularScope::class)
interface RegularGraph {
    val groceryListViewModel: GroceryListViewModel
    val itemDetailViewModelFactory: ItemDetailViewModel.ItemDetailViewModelFactory
    val groceryRepository: GroceryRepository
}