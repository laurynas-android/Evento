package com.laurynas.evento.extensions

import kotlinx.coroutines.CancellationException

inline fun <R> runCatchingCoroutine(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}