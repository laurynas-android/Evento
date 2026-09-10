package com.laurynas.evento.extensions

import androidx.compose.ui.Modifier

inline fun Modifier.ifTrue(
    value: Boolean,
    ifTrue: () -> Modifier
): Modifier {
    return if(value) {
        then(ifTrue())
    } else {
        this
    }
}