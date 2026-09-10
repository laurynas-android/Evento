package com.laurynas.evento.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GroceryItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val isBought: Boolean = false
)
