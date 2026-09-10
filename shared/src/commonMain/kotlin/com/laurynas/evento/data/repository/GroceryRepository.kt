package com.laurynas.evento.data.repository

import com.laurynas.evento.data.model.GroceryItem
import dev.zacsweers.metro.Inject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

@Inject
class GroceryRepository(private val client: HttpClient) {
    private val _items = mutableListOf(
        GroceryItem("1", "Apples", 5, false),
        GroceryItem("2", "Milk", 1, true),
        GroceryItem("3", "Bread", 2, false)
    )

    suspend fun getItems(): List<GroceryItem> = _items.toList()

    suspend fun getItemById(id: String): GroceryItem? = _items.find { it.id == id }

    suspend fun addItem(item: GroceryItem): GroceryItem {
        val newItem = item.copy(id = (_items.size + 1).toString())
        _items.add(newItem)
        return newItem
    }

    suspend fun removeItem(itemId: String) {
        _items.removeAll { it.id == itemId }
    }
}
