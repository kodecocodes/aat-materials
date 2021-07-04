package com.hackertronix.cinematic

import android.app.Application
import com.hackertronix.cinematic.data.di.dataModule
import com.hackertronix.cinematic.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(dataModule + appModule)
        }
    }
}