package com.laurynas.evento.domain

interface Logger {
    fun debug(message: String)
    fun info(message: String)
    fun warning(message: String)
    fun error(message: String, throwable: Throwable? = null)
}