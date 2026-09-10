package com.laurynas.evento.domain

import platform.Foundation.NSLog

class IosLogger : Logger {

    override fun debug(message: String) {
        NSLog("DEBUG: $message")
    }

    override fun info(message: String) {
        NSLog("INFO: $message")
    }

    override fun warning(message: String) {
        NSLog("WARNING: $message")
    }

    override fun error(
        message: String,
        throwable: Throwable?
    ) {
        if (throwable != null) {
            NSLog("ERROR: $message - ${throwable.message}")
        } else {
            NSLog("ERROR: $message")
        }
    }
}