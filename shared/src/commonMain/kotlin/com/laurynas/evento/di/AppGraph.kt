package com.laurynas.evento.di

import com.laurynas.evento.data.remote.createHttpClient
import com.laurynas.evento.domain.Logger
import com.laurynas.evento.navigation.AppNavigator
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient

@DependencyGraph(AppScope::class)
interface AppGraph {

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides logger: Logger): AppGraph
    }

    val regularGraph: RegularGraph  // exposes the child graph

    val httpClient: HttpClient
    val appNavigator: AppNavigator
    val logger: Logger

    @Provides
    @SingleIn(AppScope::class)
    fun provideHttpClient(): HttpClient = createHttpClient()

    @Provides
    @SingleIn(AppScope::class)
    fun provideNavManager(): AppNavigator = AppNavigator()

}
