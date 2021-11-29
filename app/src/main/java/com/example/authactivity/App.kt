package com.example.authactivity

import android.app.Application
import com.example.authactivity.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@App)
            inject()
        }
    }

    fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(
            listOf(
                fragmentModule,
                viewModelModule,
                networkRepository,
                repositoryModule,
                databaseModule
            )
        )
    }
}