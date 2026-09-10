package com.laurynas.evento.domain

import timber.log.Timber


class AndroidLogger : Logger {

    override fun debug(message: String) {
        Timber.d(message)
    }

    override fun info(message: String) {
        Timber.i(message)
    }

    override fun warning(message: String) {
        Timber.w(message)
    }

    override fun error(
        message: String,
        throwable: Throwable?
    ) {
        Timber.e(throwable, message)
    }
}