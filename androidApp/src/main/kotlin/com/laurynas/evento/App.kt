package com.laurynas.evento

import android.app.Application
import com.laurynas.evento.di.AppGraph
import com.laurynas.evento.domain.AndroidLogger
import dev.zacsweers.metro.createGraphFactory
import timber.log.Timber

class App : Application() {

    val appGraph: AppGraph by lazy {
        createGraphFactory<AppGraph.Factory>()
            .create(AndroidLogger())
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}